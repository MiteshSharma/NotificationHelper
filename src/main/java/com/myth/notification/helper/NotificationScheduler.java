package com.myth.notification.helper;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.myth.notification.pojo.NotificationType;
import com.myth.notification.receiver.NotificationBroadcastReceiver;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by mitesh on 07/08/15.
 */
public class NotificationScheduler {

    Context context;
    public enum Schedule {
        ONCE,
        DAILY,
        WEEKLY;
    }

    public NotificationScheduler(Context context) {
        this.context = context;
    }

    public void scheduleNotification(int notificationId, NotificationType notificationType, int alarmId, long reminderDateTime) {
        Intent mainIntent = new Intent(context, NotificationBroadcastReceiver.class);
        mainIntent.putExtra("notification_type", notificationType.name());
        mainIntent.putExtra("notification_id", ""+notificationId);
        PendingIntent pIntent = PendingIntent.getBroadcast(this.context, alarmId, mainIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) this.context.getSystemService(Context.ALARM_SERVICE);

        Long notificationTime = 0l;
        notificationTime = reminderDateTime;
        if (reminderDateTime < Calendar.getInstance().getTimeInMillis()) {
            notificationTime = Calendar.getInstance().getTimeInMillis();
        }
        alarmManager.set(AlarmManager.RTC_WAKEUP, notificationTime, pIntent);
    }

    public void scheduleNotification(String title, String message, String payload, NotificationType notificationType, int alarmId, long reminderDateTime) {
        Intent mainIntent = new Intent(context, NotificationBroadcastReceiver.class);
        mainIntent.putExtra("title", title);
        mainIntent.putExtra("message", message);
        mainIntent.putExtra("payload", ""+payload);
        mainIntent.putExtra("notification_type", notificationType.name());
        PendingIntent pIntent = PendingIntent.getBroadcast(this.context, alarmId, mainIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) this.context.getSystemService(Context.ALARM_SERVICE);

        Long notificationTime = 0l;
        notificationTime = reminderDateTime;
        if (reminderDateTime < Calendar.getInstance().getTimeInMillis()) {
            notificationTime = Calendar.getInstance().getTimeInMillis();
        }
        alarmManager.set(AlarmManager.RTC_WAKEUP, notificationTime, pIntent);
    }

    public void scheduleNotification(int notificationId, NotificationType notificationType, int alarmId, Date reminderDate, Schedule schedule) {
        Intent mainIntent = new Intent(context, NotificationBroadcastReceiver.class);
        mainIntent.putExtra("notification_type", notificationType.name());
        mainIntent.putExtra("notification_id", notificationId);
        PendingIntent pIntent = PendingIntent.getBroadcast(this.context, alarmId, mainIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) this.context.getSystemService(Context.ALARM_SERVICE);

        Calendar alarmCalendar = Calendar.getInstance();
        Calendar dateCalander = Calendar.getInstance();
        dateCalander.setTime(reminderDate);
        Long notificationTime = 0l;
        if (Schedule.ONCE.equals(schedule)) {
            notificationTime = reminderDate.getTime();
            if (reminderDate.getTime() < Calendar.getInstance().getTimeInMillis()) {
                notificationTime = Calendar.getInstance().getTimeInMillis();
            }
            alarmManager.set(AlarmManager.RTC_WAKEUP, notificationTime, pIntent);
        } else if(Schedule.DAILY.equals(schedule)) {
            alarmCalendar.set(Calendar.HOUR_OF_DAY, dateCalander.get(Calendar.HOUR_OF_DAY));
            alarmCalendar.set(Calendar.MINUTE, dateCalander.get(Calendar.MINUTE));
            alarmCalendar.set(Calendar.SECOND, dateCalander.get(Calendar.SECOND));
            notificationTime = alarmCalendar.getTimeInMillis();
            if (alarmCalendar.getTimeInMillis() < Calendar.getInstance().getTimeInMillis()) {
                notificationTime += 86400000;
            }
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, notificationTime, AlarmManager.INTERVAL_DAY, pIntent);
        } else if (Schedule.WEEKLY.equals(schedule)) {
            alarmCalendar.set(Calendar.DAY_OF_WEEK, dateCalander.get(Calendar.DAY_OF_WEEK));
            alarmCalendar.set(Calendar.HOUR_OF_DAY, dateCalander.get(Calendar.HOUR_OF_DAY));
            alarmCalendar.set(Calendar.MINUTE, dateCalander.get(Calendar.MINUTE));
            alarmCalendar.set(Calendar.SECOND, dateCalander.get(Calendar.SECOND));
            notificationTime = alarmCalendar.getTimeInMillis();
            if (alarmCalendar.getTimeInMillis() < Calendar.getInstance().getTimeInMillis()) {
                notificationTime += 86400000*7;
            }
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, notificationTime, AlarmManager.INTERVAL_DAY*7, pIntent);
        }
    }

    public void cancelNotification(int alarmId) {
        Intent mainIntent = new Intent(context, NotificationBroadcastReceiver.class);
        PendingIntent pIntent = PendingIntent.getBroadcast(this.context, alarmId, mainIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pIntent);
    }
}
