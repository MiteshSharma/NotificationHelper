package com.myth.notification;

import android.content.Context;

import com.myth.notification.listeners.INotificationService;
import com.myth.notification.pojo.NotificationType;

/**
 * Created by mitesh on 07/08/15.
 */
public class NotificationHandlerFactory {

    public static NotificationHandler getNotificationHandler(Context context, NotificationType notificationType) {
        NotificationHandler notificationHandler = null;
        INotificationService notificationService = (INotificationService)context.getApplicationContext();
        switch (notificationType) {
            case PUSH_NOTIFICATION:
                notificationHandler = notificationService.getPushNotificationHandler(context);
                break;
            case APP_NOTIFICATION:
                notificationHandler = notificationService.getAppNotificationHandler(context);
                break;
            default:
                break;
        }
        return notificationHandler;
    }
}
