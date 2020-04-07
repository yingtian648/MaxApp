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
    }

}