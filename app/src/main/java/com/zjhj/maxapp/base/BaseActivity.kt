package com.zjhj.maxapp.base

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.zjhj.maxapp.App
import com.zjhj.maxapp.R
import com.zjhj.maxapp.theme.ThemeChangeObserver
import com.zjhj.maxapp.utils.L


/**
 * CreateTime 2020/4/2 09:10
 * Author LiuShiHua
 * Description：
 */
abstract class BaseActivity : AppCompatActivity(), ThemeChangeObserver {

    private val KEY_THEME_TAG = "myThemeTag"
    private var isChangeTheme: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setThemeBeforeCreate()
        super.onCreate(savedInstanceState)
        setContentView()
        initView()
        initData()
        getData()
    }

    /**
     */
    private fun setThemeBeforeCreate() {
        App.registerObserver(this)//将当前Acitivity注册成观察者
        loadingCurrentTheme()
    }

    /**
     * 加载当前主题
     * 需要在super.onCreate(savedInstanceState)之前执行才会生效
     */
    override fun loadingCurrentTheme() {
        when (getThemeTag()) {
            1 -> setTheme(R.style.theme_day_chi_sim)
            -1 -> setTheme(R.style.theme_night_eng)
        }
        L.d("loadingCurrentTheme:" + this::class.java)
    }

    abstract fun setContentView()
    abstract fun initView()
    abstract fun initData()
    abstract fun getData()

    /**
     * 获取当前主题类型
     */
    protected open fun getThemeTag(): Int {
        val preferences = getSharedPreferences("MaxTheme", Context.MODE_PRIVATE)
        return preferences.getInt(KEY_THEME_TAG, 1)
    }

    /**
     * 设置主题类型
     * 保存在sharedprferences中
     */
    protected open fun setThemeTag(tag: Int) {
        L.d("setThemeTag:" + tag)
        val preferences = getSharedPreferences("MaxTheme", Context.MODE_PRIVATE)
        val edit = preferences.edit()
        edit.putInt(KEY_THEME_TAG, tag)
        edit.commit()
        App.notifyByThemeChanged()
    }

    /**
     * 注销这个观察者
     */
    override fun onDestroy() {
        App.unregisterObserver(this)
        super.onDestroy()
    }
}