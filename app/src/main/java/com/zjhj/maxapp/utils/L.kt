package com.zjhj.maxapp.utils

import android.util.Log

/**
 * CreateTime 2020/4/7 10:10
 * Author LiuShiHua
 * Description：
 */
class L {
    companion object {
        //静态
        private val TAG = "--------->"

        fun d(msg: String) {
            Log.d(TAG, msg)
        }

        fun e(msg: String) {
            Log.e(TAG, msg)
        }

        fun e(msg: String, throwable: Throwable) {
            Log.e(TAG, msg + "\n出错位置(" + lineNum(throwable) + ")")
        }

        fun lineNum(throwable: Throwable): String? {
            val trace = throwable.stackTrace
            // 下标为0的元素是上一行语句的信息, 下标为1的才是调用printLine的地方的信息
            val tmp = trace[0]
            return (tmp.className + "." + tmp.methodName
                    + "(" + tmp.fileName + ":" + tmp.lineNumber + ")")
        }
    }

}