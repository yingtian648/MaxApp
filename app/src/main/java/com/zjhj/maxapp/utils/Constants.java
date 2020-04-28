package com.zjhj.maxapp.utils;

import android.os.Environment;

public class Constants {

    /**
     * 视频对讲时长配置
     */
    public static final long VIDEOCHAT_TIME_OVER_LONG = 6 * 60 * 1000;//配置视频对讲等待时长
    public static final long VIDEOCHAT_TIME_SHOW_BG_LONG = 30 * 1000;//配置结束后背景显示时长
//    public static final long VIDEOCHAT_TIME_OVER_LONG = 30 * 1000;//配置视频对讲等待时长(测试)
//    public static final long VIDEOCHAT_TIME_SHOW_BG_LONG = 10 * 1000;//配置结束后背景显示时长（测试）

    /**
     * 息屏相关配置
     */
    public static final long TIME_ALARM_TO_SCREENOFF_DELAY = 45 * 60 * 1000;//配置报警到检测息屏席间【默认45分钟】
    public static final long TIME_UNALARM_TO_SCREENOFF_DELAY = 5 * 60 * 1000;//误报警到检测息屏席间
//    public static final long TIME_ALARM_TO_SCREENOFF_DELAY = 2 * 60 * 1000;//配置报警到检测息屏席间 测试使用
//    public static final long TIME_UNALARM_TO_SCREENOFF_DELAY = 30 * 1000;//误报警到检测息屏席间 测试使用


    /**
     * usb检测相关
     * 1.待安装的APP应放于更目录下，命名规范[Release_mix_1.1.0_cover.apk 覆盖更新,Release_mix_1.1.0_uninstall.apk 卸载更新]
     * 解析：Release发布版 mix标识MIX设备 1.1.0版本号【与当前正在使用的版本号不同，则去更新】
     * 2.播控包...待处理
     * 3.固件包...不处理
     */
    public static final String APP_UPDATE_NameRegex = "^(Release_mix_)[0-9.]{5,8}(_cover.apk|_uninstall.apk)$";

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
