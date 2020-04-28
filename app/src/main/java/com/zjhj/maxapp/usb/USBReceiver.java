package com.zjhj.maxapp.usb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.zjhj.maxapp.utils.L;


import java.io.File;

/**
 * CreateTime 2020/4/27 13:42
 * Author LiuShiHua
 * Description：
 */
public class USBReceiver extends BroadcastReceiver {
    private final String USB_DEVICE_ATTACHED = "android.hardware.usb.action.USB_DEVICE_ATTACHED";
    private final String USB_DEVICE_DETACHED = "android.hardware.usb.action.USB_DEVICE_DETACHED";
    private final String ACTION_MEDIA_MOUNTED = Intent.ACTION_MEDIA_MOUNTED;//媒体安装 - 获取USB路径需在媒体安装完成后获取

    @Override
    public void onReceive(Context context, Intent intent) {
        L.Companion.d("收到广播：" + intent.getAction());
        String action = intent.getAction();
        if (ACTION_MEDIA_MOUNTED.equals(action)) {//usb插入
            L.Companion.d("usb已安装");
            if (intent.getData() != null && intent.getData().getPath() != null) {
                checkUsbFileList(context, intent.getData().getPath());
            }
        }
        if (USB_DEVICE_DETACHED.equals(action)) {//usb拔出
            L.Companion.d("usb拔出");
        }
    }

    private void checkUsbFileList(Context context, String usbPath) {
        L.Companion.d("usb路径：" + usbPath);
        File file = new File(usbPath);
        if (file.exists() && file.isDirectory()) {
            String[] files = file.list();
            for (int i = 0; i < files.length; i++) {
                L.Companion.d("usb文件：" + files[i]);
            }
        }
    }
}
