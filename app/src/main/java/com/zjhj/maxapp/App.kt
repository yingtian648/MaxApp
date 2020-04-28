package com.zjhj.maxapp

import android.app.Application
import android.content.Context

/**
 * CreateTime 2020/4/9 09:40
 * Author LiuShiHua
 * Description：
 */
class App : Application() {


    companion object {
        lateinit var context: Context
        get
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }

}