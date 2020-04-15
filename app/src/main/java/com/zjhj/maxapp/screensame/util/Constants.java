package com.zjhj.maxapp.screensame.util;

import android.os.Environment;

import java.security.PublicKey;

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


    public static final int EVENT_TYPE_RECEIVE_MSG = 1101;//收到消息
    public static final int EVENT_TYPE_SEND_MSG = 1102;//发送消息

    /**
     * socket消息类型
     * 发送时：添加到内容第2位
     * 接收时：取第一位做数据类型识别
     */
    public static final Byte MSG_TYPE_STRING = 0x00; //字符串消息
    public static final Byte MSG_TYPE_IMAGE = 0x01;//图片消息
    public static final Byte MSG_TYPE_UNKNOWN = (byte) 0xff;//未知消息
    /**
     * socket消息内容完整性校验
     * 发送时：添加到内容第2位
     * 接收时：取第一位做数据类型识别
     */
    public static final Byte MSG_CONTEND_START = (byte) 0xff;
    public static final Byte MSG_CONTEND_END = (byte) 0xed;
}
