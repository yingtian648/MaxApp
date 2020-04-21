package com.zjhj.maxapp.screensame.localServer

/**
 * CreateTime 2020/4/21 17:46
 * Author LiuShiHua
 * Description：
 */
enum class HttpResponseStatus {
    REQUEST_OK(200, "请求成功"),
    REQUEST_ERROR(500, "请求失败"),
    REQUEST_ERROR_API(501, "无效的请求接口"),
    REQUEST_ERROR_CMD(502, "无效命令"),
    REQUEST_ERROR_DEVICEID(503, "不匹配的设备ID"),
    REQUEST_ERROR_ENV(504, "不匹配的服务环境");

    val requestStatus:Int
    get
    val description:String
    get

    constructor (requestStatus: Int, description: String) {
        this.requestStatus = requestStatus;
        this.description = description;
    }
}