package com.zjhj.maxapp.http

/**
 * CreateTime 2020/4/7 11:04
 * Author LiuShiHua
 * Description：
 */
class Urls {
    companion object {
        const val VERSION = "1.0.1"
        const val SN = "SNE3975D298EAD94"
        const val BASE_URL = "http://api.t4.2012iot.com";//测试地址
        const val BASE_WECHAT_PYTHON = "$BASE_URL/api-python-wechat"

        val getDevEvInfo = "$BASE_WECHAT_PYTHON/getEvMainInfo?sn=$SN"
    }
}