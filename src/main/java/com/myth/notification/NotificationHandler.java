package com.myth.notification;

import android.content.Context;

import com.myth.notification.pojo.NotificationObject;

/**
 * Created by mitesh on 07/08/15.
 */
public abstract class NotificationHandler {

    protected  Context context;

    public NotificationHandler(Context context) {
        this.context = context;
    }

    public abstract void onHandle(NotificationObject notificationObject);
}
