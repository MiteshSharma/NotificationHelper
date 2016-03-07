package com.myth.notification.helper;

import android.app.Service;
import android.content.Context;
import android.os.PowerManager;

/**
 * Created by mitesh on 07/08/15.
 */
public class WakeLocker {
    private static PowerManager.WakeLock wakeLock;

    public static void acquire(Context ctx) {
        if (wakeLock != null) wakeLock.release();

        PowerManager pm = (PowerManager) ctx.getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |
                PowerManager.ACQUIRE_CAUSES_WAKEUP |
                PowerManager.ON_AFTER_RELEASE, "MYAPPTAG");
        wakeLock.acquire();
    }

    public static void acquire(Service service) {
        if (wakeLock != null) wakeLock.release();

        PowerManager pm = (PowerManager) service.getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |
                PowerManager.ACQUIRE_CAUSES_WAKEUP |
                PowerManager.ON_AFTER_RELEASE, "MYAPPTAG");
        wakeLock.acquire();
    }

    public static void release() {
        if (wakeLock != null) {
            try {
                wakeLock.release();
            } catch (Throwable th) {
                // ignoring this exception, probably wakeLock was already released
            } finally {
                wakeLock = null;
            }
        }
    }
}
