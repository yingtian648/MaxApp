package com.zjhj.maxapp

import android.app.Application
import android.content.Context
import com.zjhj.maxapp.theme.ThemeChangeObserver


/**
 * CreateTime 2020/4/9 09:40
 * Author LiuShiHua
 * Description：
 */
class App : Application() {


    companion object {
        lateinit var context: Context
        get

        private var mThemeChangeObserverStack: MutableList<ThemeChangeObserver>? = null

        /**
         * 获得observer堆栈
         */
        private fun obtainThemeChangeObserverStack(): MutableList<ThemeChangeObserver>? {
            if (mThemeChangeObserverStack == null) mThemeChangeObserverStack = ArrayList()
            return mThemeChangeObserverStack
        }

        /**
         * 向堆栈中添加observer
         */
        fun registerObserver(observer: ThemeChangeObserver?) {
            if (observer == null || obtainThemeChangeObserverStack()!!.contains(observer)) return
            obtainThemeChangeObserverStack()!!.add(observer)
        }

        /**
         * 从堆栈中移除observer
         */
        fun unregisterObserver(observer: ThemeChangeObserver?) {
            if (observer == null || !obtainThemeChangeObserverStack()!!.contains(observer)) return
            obtainThemeChangeObserverStack()!!.remove(observer)
        }

        /**
         * 向堆栈中所有对象发送更新UI的指令
         */
        fun notifyByThemeChanged() {
            val observers: List<ThemeChangeObserver>? = obtainThemeChangeObserverStack()
            for (observer in observers!!) {
                observer.loadingCurrentTheme() //
                observer.notifyByThemeChanged() //
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}