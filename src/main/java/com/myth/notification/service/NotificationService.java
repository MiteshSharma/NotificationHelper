package com.myth.notification.service;

import android.app.IntentService;
import android.content.Intent;

import com.myth.notification.NotificationHandler;
import com.myth.notification.NotificationHandlerFactory;
import com.myth.notification.pojo.NotificationObject;
import com.myth.notification.pojo.NotificationType;
import com.myth.utility.UserSharedPreferences;

/**
 * Created by mitesh on 07/08/15.
 */
public class NotificationService extends IntentService {

    public NotificationService() {
        super("Default");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public NotificationService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        boolean isNotificationEnabled = UserSharedPreferences.getInstance(getApplicationContext()).get("notification_status", true);
        if (!isNotificationEnabled) {
            return;
        }

        int notificationId = intent.getIntExtra("notification_id", 0);
        String notificationTypeStr = intent.getStringExtra("notification_type");
        String title = intent.getStringExtra("title");
        String message = intent.getStringExtra("message");
        String payload = intent.getStringExtra("payload");
        if (notificationTypeStr == null) {
            notificationTypeStr = "APP_NOTIFICATION"
        }
        NotificationType notificationType = NotificationType.valueOf(notificationTypeStr);

        NotificationObject notificationObject = new NotificationObject();
        notificationObject.setNotificationId(notificationId);
        notificationObject.setTitle(title);
        notificationObject.setMessage(message);
        notificationObject.setPayload(payload);

        NotificationHandler notificationHandler = NotificationHandlerFactory.getNotificationHandler(this.getApplicationContext(), notificationType);
        notificationHandler.onHandle(notificationObject);
    }
}
