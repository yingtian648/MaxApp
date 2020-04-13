package com.zjhj.maxapp.screensame;

import android.os.Environment;

public class Constants {

    /**
     * Environment.getExternalStoragePublicDirectory(“”).getAbsolutePath() = /storage/emulated/0
     * 这个方法是获取外部存储的根路径
     * <p>
     * 截屏命令
     */
    public static final String command_screenshats = "/system/bin/screencap -p " +
            Environment.getExternalStorageDirectory().getAbsolutePath() +
            "/zjhj.com.dev/screenshots.jpg";

    /**
     * 录屏相关
     */
    public static final String TYPE_FLAG_NAME = "TYPE_FLAG";//传递类型名
    public static final int TYPE_SHOTSCREEN = 1;//[返回]类型-截屏
    public static final int TYPE_RECORDSCREEN = 2;//[返回]类型-录屏
    public static final int TYPE_SHOTSCREEN_FAILURE = 3;//返回类型-截屏-失败
    public static final int TYPE_RECORDSCREEN_FAILURE = 4;//返回类型-录屏-失败
    public static final int TYPE_GET_DEVINFO = 5;//返回类型-获取电梯数据

    public static final int TYPE_PUBLISH_MESSAGE = 999;//返回类型-MQtt发送消息

}
