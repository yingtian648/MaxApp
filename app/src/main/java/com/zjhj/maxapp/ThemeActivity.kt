package com.zjhj.maxapp

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.zjhj.maxapp.base.BaseActivity
import com.zjhj.maxapp.bean.DevInfo
import com.zjhj.maxapp.utils.L
import com.zjhj.maxapp.vm.DevInfoVM
import kotlinx.android.synthetic.main.activity_theme.*

class ThemeActivity : BaseActivity() {
    private lateinit var model: DevInfoVM

    override fun setContentView() {
        model = ViewModelProviders.of(this).get(DevInfoVM::class.java)
        model.getDev().observe(this, Observer<DevInfo> { devInfo ->
            // update UI1
            L.d("更新视图:" + devInfo?.values?.mainCompany)
            titlebar.title = devInfo?.values?.mainCompany
        })
        setContentView(R.layout.activity_theme)
    }

    override fun onDestroy() {
        super.onDestroy()
        L.d("ThemeActivity onDestroy")
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
