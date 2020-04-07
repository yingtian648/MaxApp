package com.zjhj.maxapp.http

/**
 * CreateTime 2020/4/7 14:18
 * Author LiuShiHua
 * Description：
 */
class HttpMsgUtil {
    companion object {
        fun getHttpMsg(msgRes: String?): String {
            var msg: String = ""
            if (msgRes != null && msgRes !== "") {
                if (msgRes.endsWith("out") || msgRes.startsWith("failed to connect") ||
                    msgRes.contains("connect") || msgRes.contains("timeout") ||
                    msgRes.contains("Unable to resolve host")
                ) {
                    msg = "网络连接超时，请检查网络"
                } else {
                    msg = "网络请求错误"
                }
            } else {
                msg = "网络请求错误，请检查网络"
            }
            if (msgRes != null && msgRes.contains("No such file")) {
                msg = "操作失败，相关文件丢失"
            }
            return msg
        }
    }
}