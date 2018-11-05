package com.existek.existek.existek.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.existek.existek.existek.R;

public class AlarmReceiver extends BroadcastReceiver {

    private final String channelId = "com.existek.existek.channel_01";
    private final String channelName = "Existek";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationsManager reminderManager = new NotificationsManager(context);
        sendNotification(context);
        reminderManager.startReminder();
    }

    @TargetApi(Build.VERSION_CODES.O)
    private NotificationChannel getNotificationChannel(Context context) {
        NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, android.app.NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
        notificationChannel.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION), null);
        notificationChannel.setShowBadge(true);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setLightColor(ContextCompat.getColor(context, R.color.colorPrimary));
        notificationChannel.setShowBadge(true);
        notificationChannel.setVibrationPattern(new long[]{500, 500, 500, 500});
        notificationChannel.setImportance(android.app.NotificationManager.IMPORTANCE_HIGH);

        return notificationChannel;
    }

    public void sendNotification(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), channelId)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setStyle(new NotificationCompat.BigTextStyle())
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setPriority(Notification.PRIORITY_HIGH)
                .setCategory(Notification.CATEGORY_SOCIAL)
                .setVibrate(new long[]{500, 500, 500, 500})
                .setLights(ContextCompat.getColor(context, R.color.colorPrimary), 2000, 2000)
                .setAutoCancel(true)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(context.getString(R.string.title_notification));

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(getNotificationChannel(context));
            }
        }

        if (notificationManager != null) {
            notificationManager.notify(0, builder.build());
        }
    }
}
