package ru.linachan.pushbullet;

import ru.linachan.yggdrasil.component.YggdrasilPlugin;

public class PushBulletPlugin extends YggdrasilPlugin {

    private PushBulletClient client;

    @Override
    protected void setUpDependencies() {

    }

    @Override
    protected void onInit() {
        String apiKey = core.getConfig().getString("push_bullet.api.key", null);

        client = new PushBulletClient(core, apiKey);
        client.setUpDevice("Yggdrasil");
    }

    @Override
    protected void onShutdown() {

    }

    public PushBulletClient getClient() {
        return client;
    }
}
