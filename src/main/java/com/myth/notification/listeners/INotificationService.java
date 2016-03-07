package com.myth.notification.listeners;

import android.content.Context;

import com.myth.notification.NotificationHandler;

/**
 * Created by mitesh on 06/03/16.
 */
public interface INotificationService {
    public NotificationHandler getPushNotificationHandler(Context context);
    public NotificationHandler getAppNotificationHandler(Context context);
}
