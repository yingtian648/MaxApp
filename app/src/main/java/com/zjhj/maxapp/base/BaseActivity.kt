package com.zjhj.maxapp.base

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.Window
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
abstract class BaseActivity : AppCompatActivity() , ThemeChangeObserver {

    private val KEY_MARIO_CACHE_THEME_TAG = "myThemeTag"

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
        App.registerObserver(this)
        loadingCurrentTheme()
    }

    override fun loadingCurrentTheme() {
        when (getThemeTag()) {
            1 -> setTheme(R.style.theme_day_chi_sim)
            -1 -> setTheme(R.style.theme_night_eng)
        }
    }

    abstract fun setContentView()
    abstract fun initView()
    abstract fun initData()
    abstract fun getData()

    /**
     */
    protected open fun getThemeTag(): Int {
        val preferences = getSharedPreferences("MaxTheme", Context.MODE_PRIVATE)
        return preferences.getInt(KEY_MARIO_CACHE_THEME_TAG, 1)
    }

    /**
     */
    protected open fun setThemeTag(tag: Int) {
        L.d("setThemeTag:"+tag)
        val preferences = getSharedPreferences("MaxTheme", Context.MODE_PRIVATE)
        val edit = preferences.edit()
        edit.putInt(KEY_MARIO_CACHE_THEME_TAG, tag)
        edit.commit()
    }

    /**
     */
    private fun switchCurrentThemeTag() {
        setThemeTag(0 - getThemeTag())
        loadingCurrentTheme()
    }

    override fun onDestroy() {
        App.unregisterObserver(this)
        super.onDestroy()
    }

}