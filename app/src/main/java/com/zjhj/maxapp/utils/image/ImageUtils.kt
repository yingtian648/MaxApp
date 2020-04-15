package com.zjhj.maxapp.utils.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

/**
 * CreateTime 2020/4/15 10:45
 * Author LiuShiHua
 * Description：
 */
class ImageUtils {
    companion object {
        fun getBitmapFromByteArray(bytes: ByteArray): Bitmap {
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
        }

        private fun getBitmapFromFile(filePath: String): Bitmap {
            return BitmapFactory.decodeFile(filePath)
        }

        fun bitmap2BytesPng(bm: Bitmap): ByteArray? {
            val baos = ByteArrayOutputStream()
            bm.compress(Bitmap.CompressFormat.PNG, 100, baos)
            return baos.toByteArray()
        }

        fun bitmap2BytesJpg(bm: Bitmap): ByteArray? {
            val baos = ByteArrayOutputStream()
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            return baos.toByteArray()
        }

        fun getFileBitmapBytesPng(filePath: String): ByteArray? {
            return bitmap2BytesPng(getBitmapFromFile(filePath))
        }

        fun getFileBitmapBytesJpg(filePath: String): ByteArray? {
            return bitmap2BytesJpg(getBitmapFromFile(filePath))
        }
    }
}