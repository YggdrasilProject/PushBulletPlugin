package ru.linachan.pushbullet.objects;

import org.json.simple.JSONObject;

public class PushBulletDevice extends PushBulletObject {

    private boolean active = true;
    private boolean pushable = false;
    private Double created = null;
    private Double modified = null;
    private Long app_version = null;
    private String iden = null;
    private String nickname = null;
    private String type = null;
    private String kind = null;
    private String manufacturer = null;
    private String model = null;
    private String fingerprint= null;
    private String push_token = null;
    private PushBulletDeviceIcon icon;

    public PushBulletDevice(JSONObject deviceData) {
        super(deviceData);

        active = getBool("active");
        pushable = getBool("pushable");
        created = getDouble("created");
        modified = getDouble("modified");
        app_version = getLong("app_version");
        iden = getString("iden");
        nickname = getString("nickname");
        type = getString("type");
        kind = getString("kind");
        manufacturer = getString("manufacturer");
        model = getString("model");
        fingerprint = getString("fingerprint");
        push_token = getString("push_token");
        icon = PushBulletDeviceIcon.valueOf(getString("icon").toUpperCase());
    }

    public boolean isActive() {
        return active;
    }

    public boolean isPushable() {
        return pushable;
    }

    public Double getCreated() {
        return created;
    }

    public Double getModified() {
        return modified;
    }

    public Long getAppVersion() {
        return app_version;
    }

    public String getIden() {
        return iden;
    }

    public String getNickName() {
        return nickname;
    }

    public String getType() {
        return type;
    }

    public String getKind() {
        return kind;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getFingerPrint() {
        return fingerprint;
    }

    public String getPushToken() {
        return push_token;
    }

    public String toString() {
        return nickname;
    }

    public PushBulletDeviceIcon getIcon() {
        return icon;
    }
}
