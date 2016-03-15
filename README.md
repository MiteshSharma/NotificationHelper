# NotificationHelper
Notification helper library which helps in sending notification. 
Complete notification support code is added in this library which can be used to directly give final notification to respective class. 
This library support two kinds of notification. Push notification or app notification.

Please add NotificationBroadcastReceiver as notification receiver in your AndroidManifest which is located at : com.myth.notification.receiver.NotificationBroadcastReceiver.java

Implement INotificationService interface to provide where you want to handle these notifications.

You can use this as module in your project and upload this in your artifactory repository to use later. Just add : classpath "org.jfrog.buildinfo:build-info-extractor-gradle:4.0.1" to your main .gradle file and upload .aar file on your artifactory repository.
