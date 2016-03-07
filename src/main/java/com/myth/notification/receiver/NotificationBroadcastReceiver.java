package com.myth.notification.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.myth.notification.service.NotificationService;
import com.myth.notification.helper.WakeLocker;

/**
 * Created by mitesh on 07/08/15.
 */
public class NotificationBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        WakeLocker.acquire(context);
        Intent serviceIntent = new Intent(context, NotificationService.class);
        serviceIntent.putExtras(intent);
        context.startService(serviceIntent);
        WakeLocker.release();
    }

}
