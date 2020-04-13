package com.zjhj.maxapp.utils

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.os.Handler
import android.os.Message
import android.text.format.Formatter
import android.widget.Toast
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.*

/**
 * CreateTime 2020/4/9 16:39
 * Author LiuShiHua
 * Description：
 */
class FileUtil {
    companion object {
        const val SAVE_FILE_SUCCESS = 34
        const val SAVE_FILE_FAILURE = 51
        const val SAVE_FILE_PERSENT = 68

        fun copyFile(context: Context, resPath: String?, savePath: String?) {
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
                    (context as Activity).runOnUiThread({
                        Toast.makeText(context, "复制成功:"+savePath, Toast.LENGTH_SHORT).show()
                    })
                } catch (e: Exception) {
                    L.d("")
                    L.d("copyFile Exception:" + e.message)
                    (context as Activity).runOnUiThread({
                        Toast.makeText(context, "复制失败 Exception:" + e.message, Toast.LENGTH_SHORT).show()
                    })
                }
            }
        }

        fun saveBitmapToImage(bitmap: Bitmap?, savePath: String,handler: Handler?) {
            val file = File(savePath)
            if (bitmap != null) {
                try {
                    if (!file.exists()) {
                        file.parentFile.mkdirs()
                        file.createNewFile()
                    }
                    val baos = ByteArrayOutputStream()
                    bitmap.compress(CompressFormat.JPEG, 100, baos)
                    val fos = FileOutputStream(file)
                    fos.write(baos.toByteArray())
                    fos.flush()
                    fos.close()
                    baos.close()
                    if (handler != null) {
                        val message = Message()
                        message.what = 34
                        message.obj = savePath
                        handler.sendMessage(message)
                    }
                } catch (var5: IOException) {
                    var5.printStackTrace()
                    handler?.sendEmptyMessage(51)
                }
            } else handler?.sendEmptyMessage(51)

        }
    }
}