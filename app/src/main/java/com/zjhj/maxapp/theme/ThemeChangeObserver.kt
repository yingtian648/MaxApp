package com.zjhj.maxapp.theme

interface ThemeChangeObserver {
    /**
     * 加载当前主题
     */
    fun loadingCurrentTheme()

    /**
     * 主题改变后通知
     */
    fun notifyByThemeChanged()
}