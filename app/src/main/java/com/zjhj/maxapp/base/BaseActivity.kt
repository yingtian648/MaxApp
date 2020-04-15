package com.zjhj.maxapp.base

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

/**
 * CreateTime 2020/4/2 09:10
 * Author LiuShiHua
 * Description：
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        window.addFlags(WindowManager.LayoutParams.WRAP_CONTENT)
        super.onCreate(savedInstanceState)
        setContentView()
        initView()
        initData()
        getData()
    }

    abstract fun setContentView()
    abstract fun initView()
    abstract fun initData()
    abstract fun getData()


}