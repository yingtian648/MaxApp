package com.zjhj.maxapp.app_protect;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;

/**
 * CreateTime 2020/4/26 17:59
 * Author LiuShiHua
 * Description：
 */

public class GrayService extends Service {

    private final static int GRAY_SERVICE_ID = 1001;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(GRAY_SERVICE_ID, new Notification());
        stopForeground(true);
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}


