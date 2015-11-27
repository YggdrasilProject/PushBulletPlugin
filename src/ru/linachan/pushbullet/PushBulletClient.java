package ru.linachan.pushbullet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.linachan.pushbullet.objects.PushBulletDevice;
import ru.linachan.pushbullet.objects.PushBulletDeviceIcon;
import ru.linachan.pushbullet.objects.PushBulletPush;
import ru.linachan.pushbullet.objects.PushBulletPushType;
import ru.linachan.yggdrasil.YggdrasilCore;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class PushBulletClient {

    private YggdrasilCore core;

    private final String API_KEY;
    private final String API_VERSION = "v2";

    private PushBulletDevice device;

    private static Logger logger = LoggerFactory.getLogger(PushBulletClient.class);

    public PushBulletClient(YggdrasilCore yggdrasilCore, String apiKey) {
        core = yggdrasilCore;

        API_KEY = apiKey;
    }

    private URL buildURL(String endpoint) throws MalformedURLException {
        return new URL("https://api.pushbullet.com/" + API_VERSION + "/" + endpoint);
    }

    private JSONObject callAPI(PushBulletRequestType method, String endpoint, JSONObject data) {
        try {
            HttpURLConnection apiConnection = (HttpURLConnection) buildURL(endpoint).openConnection();

            apiConnection.setRequestMethod(method.name());
            apiConnection.setRequestProperty("Access-Token", API_KEY);

            switch (method) {
                case GET:
                case DELETE:
                    break;
                case PUT:
                case POST:
                    apiConnection.setRequestProperty("Content-Type", "application/json");
                    apiConnection.setDoOutput(true);
                    DataOutputStream output = new DataOutputStream(apiConnection.getOutputStream());

                    output.writeBytes(data.toJSONString());

                    output.flush();
                    output.close();
                    break;
            }

            return (JSONObject) new JSONParser().parse(new InputStreamReader(apiConnection.getInputStream()));
        } catch (IOException | ParseException e) {
            logger.trace("Unable to call PushBullet API", e);
        }

        return null;
    }

    public List<PushBulletDevice> listDevices() {
        JSONObject response = callAPI(PushBulletRequestType.GET, "devices", null);
        List<PushBulletDevice> devices = new ArrayList<>();

        if (response != null) {
            for (Object device : (JSONArray) response.get("devices")) {
                devices.add(new PushBulletDevice((JSONObject) device));
            }
        }

        return devices;
    }

    public PushBulletDevice getDevice(String nickName) {
        for (PushBulletDevice device: listDevices()) {
            if (device.getNickName().equals(nickName)) {
                return device;
            }
        }

        return null;
    }

    public PushBulletDevice createDevice(JSONObject deviceData) {
        JSONObject response = callAPI(PushBulletRequestType.POST, "devices", deviceData);
        return new PushBulletDevice(response);
    }

    public PushBulletDevice createDevice(String nickName, String model, String manufacturer, String pushToken, int version, PushBulletDeviceIcon icon, boolean hasSMS) {
        JSONObject deviceData = new JSONObject();

        deviceData.put("nickname", nickName);
        deviceData.put("model", model);
        deviceData.put("manufacturer", manufacturer);
        deviceData.put("push_token", pushToken);
        deviceData.put("app_version", version);
        deviceData.put("icon", icon.toString().toLowerCase());
        deviceData.put("has_sms", hasSMS);

        return createDevice(deviceData);
    }

    public void setUpDevice(String deviceName) {
        device = getDevice(deviceName);
        device = (device != null) ? device : createDevice(
            deviceName,
            "PushBullet Plugin",
            "linaDevel Team",
            null,
            1,
            PushBulletDeviceIcon.SYSTEM,
            false
        );
    }

    public List<PushBulletPush> listPushes() {
        JSONObject response = callAPI(PushBulletRequestType.GET, "pushes", null);
        List<PushBulletPush> pushes = new ArrayList<>();

        if (response != null) {
            for (Object push : (JSONArray) response.get("pushes")) {
                pushes.add(new PushBulletPush((JSONObject) push));
            }
        }

        return pushes;
    }

    public PushBulletPush getPush(String iden) {
        for (PushBulletPush push: listPushes()) {
            if (push.getIden().equals(iden)) {
                return push;
            }
        }
        return null;
    }

    public PushBulletPush createPush(JSONObject pushData) {
        JSONObject response = callAPI(PushBulletRequestType.POST, "pushes", pushData);
        return new PushBulletPush(response);
    }

    public PushBulletPush createPush(PushBulletPushType type, JSONObject pushData) {
        JSONObject pushObject = new JSONObject();

        if (device != null) {
            pushObject.put("device_iden", device.getIden());
        }

        pushObject.put("type", type.toString().toLowerCase());

        switch (type) {
            case NOTE:
                pushObject.put("title", pushData.get("title"));
                pushObject.put("body", pushData.get("body"));
                break;
            case LINK:
                pushObject.put("title", pushData.get("title"));
                pushObject.put("body", pushData.get("body"));
                pushObject.put("url", pushData.get("url"));
                break;
            case FILE:
                pushObject.put("body", pushData.get("body"));
                pushObject.put("file_name", pushData.get("file_name"));
                pushObject.put("file_type", pushData.get("file_type"));
                pushObject.put("file_url", pushData.get("file_url"));
                break;
        }

        return createPush(pushObject);
    }

    public PushBulletPush createNotePush(String title, String body) {
        JSONObject pushData = new JSONObject();

        pushData.put("title", title);
        pushData.put("body", body);

        return createPush(PushBulletPushType.NOTE, pushData);
    }

    public PushBulletPush createLinkPush(String title, String body, String url) {
        JSONObject pushData = new JSONObject();

        pushData.put("title", title);
        pushData.put("body", body);
        pushData.put("url", url);

        return createPush(PushBulletPushType.NOTE, pushData);
    }
}
