package ru.linachan.pushbullet.objects;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PushBulletObject {

    private JSONObject objectData;

    public PushBulletObject(JSONObject objectData) {
        this.objectData = objectData;
    }

    public PushBulletObject(String objectData) throws ParseException {
        this.objectData = (JSONObject) new JSONParser().parse(objectData);
    }

    protected JSONObject getObject(String key) {
        return (objectData.containsKey(key)) ? (JSONObject) objectData.get(key) : null;
    }

    protected JSONArray getArray(String key) {
        return (objectData.containsKey(key)) ? (JSONArray) objectData.get(key) : null;
    }

    protected String getString(String key) {
        return (objectData.containsKey(key)) ? (String) objectData.get(key) : null;
    }

    protected Double getDouble(String key) {
        return (objectData.containsKey(key)) ? (Double) objectData.get(key) : null;
    }

    protected Float getFloat(String key) {
        return (objectData.containsKey(key)) ? (Float) objectData.get(key) : null;
    }

    protected Long getLong(String key) {
        return (objectData.containsKey(key)) ? (Long) objectData.get(key) : null;
    }

    protected Integer getInt(String key) {
        return (objectData.containsKey(key)) ? (Integer) objectData.get(key) : null;
    }

    protected Boolean getBool(String key) {
        return (objectData.containsKey(key)) ? (Boolean) objectData.get(key) : null;
    }
}
