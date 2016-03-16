# NotificationHelper
Notification helper library which helps in sending notification. 
Complete notification support code is added in this library which can be used to directly give final notification to respective class. 
This library support two kinds of notification. Push notification or app notification.

Please add NotificationBroadcastReceiver as notification receiver in your AndroidManifest which is located at : com.myth.notification.receiver.NotificationBroadcastReceiver.java

Implement INotificationService interface to provide where you want to handle these notifications.

You can use this as module in your project and upload this in your artifactory repository to use later. Just add : classpath "org.jfrog.buildinfo:build-info-extractor-gradle:4.0.1" to your main .gradle file and upload .aar file on your artifactory repository.

#Notification Content
Add notification_type in you message which send :

1. PUSH_NOTIFICATION
2. APP_NOTIFICATION

depending on what kind of notification it is and how you want to handle it. If no notification type defined, then it will assume APP_NOTIFICATION by default.

#Steps to start:
1. Clone this in your project and import as module. If it doesn't work directly, create new module and give that new module this repo path so it will override any old module setting and act as fresh module.
2. Implement INotificationService in your main application(which extends Application) class which provides App notification and Push notification handling class. As we take it from main application context.
3. Specify com.myth.notification.receiver.NotificationBroadcastReceiver.java class as you broadcast receiver for notification. Google permissions for push notification and how to add broadcast receiver to handle notifications if you have trouble doing it.
4. You can schedule notifications using class NotificationScheduler.


PS: Do fix bug if you find any and help make this repo perfect. :)
