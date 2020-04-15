package com.zjhj.maxapp.screensame.socket

import com.zjhj.maxapp.screensame.util.Constants

/**
 * CreateTime 2020/4/14 17:12
 * Author LiuShiHua
 * Description：
 */
fun getMsgType(data: ByteArray): Byte {
    if (data.size >= 3 && data[data.lastIndex].equals(Constants.MSG_CONTEND_END) && data[1].equals(Constants.MSG_CONTEND_START)) {
        return data[0]
    }
    return Constants.MSG_TYPE_STRING
}


fun getMsgContent(data: ByteArray): ByteArray? {
    if (data.size == 3) {
        return null
    } else if (data.size > 3 && data[data.lastIndex] == Constants.MSG_CONTEND_END && data[1] == Constants.MSG_CONTEND_START) {
        return data.copyOfRange(2, data.lastIndex)
    }
    return null
}

fun addMsgTyoe(data: ByteArray?, msgType: Byte): ByteArray {
    if (data == null || data.isEmpty()) {
        return byteArrayOf(Constants.MSG_TYPE_STRING, Constants.MSG_CONTEND_START, Constants.MSG_CONTEND_END)
    } else {
        val result = data.toMutableList()
        result.add(0, msgType)
        result.add(1, Constants.MSG_CONTEND_START)
        result.add(Constants.MSG_CONTEND_END)
        return result.toByteArray()
    }
}