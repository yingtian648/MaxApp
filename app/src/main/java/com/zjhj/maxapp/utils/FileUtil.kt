package com.zjhj.maxapp.utils

import kotlinx.coroutines.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.nio.ByteBuffer

/**
 * CreateTime 2020/4/9 16:39
 * Author LiuShiHua
 * Description：
 */
class FileUtil {
    companion object {
        fun copyFile(resPath: String?, savePath: String?) {
            L.d("copy文件")
            if (resPath == null || savePath == null || !File(resPath).exists()) return
            var file = File(savePath)
            GlobalScope.launch {
                //启动协程来处理
                try {
                    if (!file.exists()) {
                        file.parentFile?.mkdirs()
                        file.createNewFile()
                    }
                    val fis = FileInputStream(File(resPath))
                    val fos = FileOutputStream(file)
                    val b = ByteArray(2048)
                    var len: Int = fis.read(b)
                    while (len > 0) {
                        fos.write(b, 0, len)
                        len = fis.read(b)
                    }
                    fos.flush()
                    fos.close()
                    fis.close()
                    L.d("复制成功")
                } catch (e: Exception) {
                    L.d("copyFile Exception："+e.message)
                }
            }
        }

        fun copyFileN(resPath: String?, savePath: String?) {
            if (resPath == null || savePath == null || !File(resPath).exists()) return
            var file = File(savePath)
            GlobalScope.launch {
                //启动协程来处理
                try {
                    if (!file.exists()) {
                        file.parentFile?.mkdirs()
                        file.createNewFile()
                    }
                    val ism = File(resPath).inputStream()
                    val fos = FileOutputStream(file)
                    val b = ByteArray(2048)
                    var len: Int = ism.read(b)
                    while (len > 0) {
                        fos.write(b, 0, len)
                        len = ism.read(b)
                    }
                    fos.flush()
                    fos.close()
                    ism.close()
                    L.d("复制成功")
                } catch (e: Exception) {
                    L.d("")
                    L.d("copyFile Exception")
                }
            }
        }
    }
}