package ru.linachan.pushbullet.objects;

import org.json.simple.JSONObject;

public class PushBulletPush extends PushBulletObject {

    private String iden = null;
    private String type = null;
    private boolean active = true;
    private boolean dismissed = false;
    private Double created = null;
    private Double modified = null;
    private String title = null;
    private String body = null;
    private String url = null;
    private String target_device_iden = null;
    private String sender_iden = null;
    private String sender_email = null;
    private String sender_email_normalized = null;
    private String receiver_iden = null;
    private String receiver_email = null;
    private String receiver_email_normalized = null;

    public PushBulletPush(JSONObject pushData) {
        super(pushData);

        iden = getString("iden");
        type = getString("type");
        active = getBool("active");
        dismissed = getBool("dismissed");
        created = getDouble("created");
        modified = getDouble("modified");
        title = getString("title");
        body = getString("body");
        url = getString("url");
        target_device_iden = getString("target_device_iden");
        sender_iden = getString("sender_iden");
        sender_email = getString("sender_email");
        sender_email_normalized = getString("sender_email_normalized");
        receiver_iden = getString("receiver_iden");
        receiver_email = getString("receiver_email");
        receiver_email_normalized = getString("receiver_email_normalized");
    }

    public String getIden() {
        return iden;
    }

    public String getType() {
        return type;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isDismissed() {
        return dismissed;
    }

    public Double getCreated() {
        return created;
    }

    public Double getModified() {
        return modified;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getUrl() {
        return url;
    }

    public String getTargetDeviceIden() {
        return target_device_iden;
    }

    public String getSenderIden() {
        return sender_iden;
    }

    public String getSenderEMail() {
        return sender_email;
    }

    public String getSenderEMailNormalized() {
        return sender_email_normalized;
    }

    public String getReceiverIden() {
        return receiver_iden;
    }

    public String getReceiverEMail() {
        return receiver_email;
    }

    public String getReceiverEMailNormalized() {
        return receiver_email_normalized;
    }

    public String toString() {
        return "[" + sender_iden + "] " + title;
    }
}
