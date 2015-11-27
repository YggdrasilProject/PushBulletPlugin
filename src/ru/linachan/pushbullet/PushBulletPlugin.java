package ru.linachan.pushbullet;

import ru.linachan.yggdrasil.component.YggdrasilPlugin;

public class PushBulletPlugin extends YggdrasilPlugin {

    private PushBulletClient client;

    @Override
    protected void setUpDependencies() {

    }

    @Override
    protected void onInit() {
        String apiKey = core.getConfig("PushBulletAPIKey", null);

        client = new PushBulletClient(core, apiKey);
        client.setUpDevice("Yggdrasil");

        PushBulletProvider provider = new PushBulletProvider();
        core.getNotificationManager().registerProvider(provider);
    }

    @Override
    protected void onShutdown() {

    }

    @Override
    protected boolean executeTests() {
        return true;
    }

    public PushBulletClient getClient() {
        return client;
    }
}
