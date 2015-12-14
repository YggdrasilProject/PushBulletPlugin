package ru.linachan.pushbullet;

import ru.linachan.yggdrasil.component.YggdrasilPlugin;
import ru.linachan.yggdrasil.notification.YggdrasilNotificationManager;

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

        PushBulletProvider provider = new PushBulletProvider();
        core.getManager(YggdrasilNotificationManager.class).registerProvider(provider);
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
