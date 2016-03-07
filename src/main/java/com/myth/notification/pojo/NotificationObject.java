package com.myth.notification.pojo;

/**
 * Created by mitesh on 07/08/15.
 */
public class NotificationObject {
    private int notificationId;
    private String title;
    private String message;
    private String payload;

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public boolean isImageNeeded() {
        return this.getPayload() != null && !"".equals(this.getPayload()) && this.getPayload().startsWith("http");
    }

    public String getImageUrl() {
        return this.getPayload();
    }
}
