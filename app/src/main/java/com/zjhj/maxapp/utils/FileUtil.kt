package com.zjhj.maxapp.utils

import android.content.Context
import android.text.format.Formatter
import kotlinx.coroutines.*
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

/**
 * CreateTime 2020/4/9 16:39
 * Author LiuShiHua
 * Description：
 */
class FileUtil {
    companion object {
        fun copyFile(context: Context,resPath: String?, savePath: String?) {
            L.d("copy文件：源文件：" + resPath + "\t保存到：" + savePath)
            if (resPath == null || savePath == null || !File(resPath).exists()) {
                L.e("源文件不存在或保存地址为空")
                return
            }
            L.d("源文件大小：" + Formatter.formatFileSize(context, File(resPath).length()))
            GlobalScope.launch {
                //启动协程来处理
                val file = File(savePath)
                try {
                    if (!file.exists()) {
                        file.parentFile?.mkdirs()
                        file.createNewFile()
                    }
                    L.d("源文件大小：" + File(resPath).length())
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
                    L.d("copyFile Exception：" + e.message)
                }
            }
        }

        fun copyFileN(context: Context, resPath: String?, savePath: String?) {
            L.d("copy文件：源文件：" + resPath + "\t保存到：" + savePath)
            if (resPath == null || savePath == null || !File(resPath).exists()) {
                L.e("源文件不存在或保存地址为空")
                return
            }
            L.d("源文件大小：" + Formatter.formatFileSize(context, File(resPath).length()))
            GlobalScope.launch {
                //启动协程来处理
                val file = File(savePath)
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