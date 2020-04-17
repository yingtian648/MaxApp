package com.zjhj.maxapp

import android.app.Application
import android.content.Context

/**
 * CreateTime 2020/4/9 09:40
 * Author LiuShiHua
 * Description：
 */
class App : Application(){
    lateinit var context: Context

    override fun onCreate() {
        super.onCreate()
        context = this

    }

}