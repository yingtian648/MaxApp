package com.zjhj.maxapp.screensame.udputil

import com.zjhj.maxapp.screensame.util.Constants

/**
 * CreateTime 2020/4/14 17:12
 * Author LiuShiHua
 * Description：
 * 消息体：消息类型+消息(头)开始标志+[消息体]+消息(尾)结束标志
 */

//获取消息类型
fun getMsgType(data: ByteArray): Byte {
    if (data.size >= 3 && data[data.lastIndex] == Constants.MSG_CONTEND_END && data[1] == Constants.MSG_CONTEND_START) {
        return data[0]
    }
    return Constants.MSG_TYPE_UNKNOWN
}

//获取消息体
fun getMsgContent(data: ByteArray): ByteArray? {
    if (data.size == 3) {
        return null
    } else if (data.size > 3 && data[data.lastIndex] == Constants.MSG_CONTEND_END && data[1] == Constants.MSG_CONTEND_START) {
        return data.copyOfRange(2, data.lastIndex)
    }
    return null
}

//添加消息头和尾
fun addMsgTyoe(data: ByteArray?, msgType: Byte): ByteArray {
    if (data == null || data.isEmpty()) {
        return byteArrayOf(msgType, Constants.MSG_CONTEND_START, Constants.MSG_CONTEND_END)
    } else {
        val result = data.toMutableList()
        result.add(0, msgType)
        result.add(1, Constants.MSG_CONTEND_START)
        result.add(Constants.MSG_CONTEND_END)
        return result.toByteArray()
    }
}