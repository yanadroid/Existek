package com.existek.existek.existek.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            NotificationsManager reminderManager = new NotificationsManager(context);
            reminderManager.startReminder();
        }
    }
}
