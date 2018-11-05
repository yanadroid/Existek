package com.existek.existek.existek.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class NotificationsManager {

    private Context context;
    private AlarmManager alarmManager;

    public NotificationsManager(Context context) {
        this.context = context;
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    public void startReminder() {
        PendingIntent pendingIntent = createPendingIntent();
        setAlarm(pendingIntent);
    }

    private void setAlarm(PendingIntent pendingIntent) {
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + AlarmManager.INTERVAL_FIFTEEN_MINUTES / 3, pendingIntent);
    }

    private PendingIntent createPendingIntent() {
        Intent intent = new Intent(context, AlarmReceiver.class);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
