package com.zjhj.maxapp

import com.zjhj.maxapp.base.BaseActivity
import kotlinx.android.synthetic.main.activity_theme.*

class ThemeActivity : BaseActivity() {
    override fun setContentView() {
        setContentView(R.layout.activity_theme)
    }

    override fun initView() {
        changeTheme.setOnClickListener {
            if (getThemeTag()==1) {
                setThemeTag(-1)
            } else {
                setThemeTag(1)
            }
            recreate()
        }
    }

    override fun initData() {

    }

    override fun getData() {

    }

    override fun notifyThemeChanged() {

    }
}
