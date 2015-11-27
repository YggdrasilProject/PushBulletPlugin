package ru.linachan.pushbullet;

import ru.linachan.yggdrasil.notification.YggdrasilNotification;
import ru.linachan.yggdrasil.notification.YggdrasilNotificationProvider;

public class PushBulletProvider extends YggdrasilNotificationProvider {

    @Override
    public void sendNotification(YggdrasilNotification notification) {
        PushBulletClient client = core.getPluginManager().getPlugin(PushBulletPlugin.class).getClient();

        client.createNotePush(
            notification.getSource() + ": " + notification.getHeader(),
            notification.getMessage()
        );
    }

}
