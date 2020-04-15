package com.zjhj.maxapp.utils

import android.os.Environment
import java.util.*

/**
 * CreateTime 2020/4/15 10:19
 * Author LiuShiHua
 * Description：
 */
class PathUtil {
    companion object {
        val BASE_PATH = Environment.getExternalStorageDirectory()?.absolutePath + "/AAAFiles"

        fun getPicTempPath(): String {
            return BASE_PATH + "/PicTemp"
        }

        fun getDownLoadFilePath(): String {
            return BASE_PATH + "/Downloads"
        }
    }
}