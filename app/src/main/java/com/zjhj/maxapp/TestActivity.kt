package com.zjhj.maxapp

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.zjhj.maxapp.utils.PathUtil
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import com.zjhj.maxapp.utils.L
import kotlinx.android.synthetic.main.activity_test.*
import java.io.FileOutputStream

class TestActivity : AppCompatActivity() {
    val filePath = PathUtil.BASE_PATH + File.separator + "advert.png"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
//        copyAccessFile()
        val uri = Uri.parse(filePath)
//        Glide.with(this).asBitmap().load(filePath).into(image)
        Picasso.with(this).load(uri).into(image)
    }

    private fun copyAccessFile() {
        GlobalScope.launch {
            try {
                val fs = resources.assets.open("advert.png")
                val file = File(filePath)
                if(!file.exists()){
                    file.parentFile?.mkdirs()
                    file.createNewFile()
                }
                val fos = FileOutputStream(file)
                fos.write(fs.readBytes())
                fos.flush()
                fos.close()
                fs.close()
                L.d("复制文件成功")
            } catch (e: Exception) {
                L.d("复制文件错误")
            }
        }
    }
}
