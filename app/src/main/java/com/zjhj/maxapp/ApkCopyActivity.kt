package com.zjhj.maxapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.zjhj.maxapp.adapter.ApkCopyAdapter
import com.zjhj.maxapp.appcopy.AppInfo
import com.zjhj.maxapp.appcopy.PackageUtil
import com.zjhj.maxapp.base.BaseActivity
import com.zjhj.maxapp.base.BaseRecyclerViewAdapter
import com.zjhj.maxapp.myview.MyLinearLayoutManager
import com.zjhj.maxapp.utils.L
import kotlinx.android.synthetic.main.activity_apk_copy.*
import kotlinx.android.synthetic.main.activity_main.recyclerView

class ApkCopyActivity  : BaseActivity(), BaseRecyclerViewAdapter.OnClickRecyclerItemListener {
    lateinit var pkutil: PackageUtil //延迟初始化，可以避免检查空

    val TAG: String = "---------->"
    val handler: Handler = Handler(Looper.getMainLooper())
    var dataList = mutableListOf<AppInfo>()
    var myAdapter = ApkCopyAdapter(this, dataList, R.layout.item_rv)

    override fun setContentView() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_apk_copy)
    }

    override fun getData() {

    }

    override fun notifyByThemeChanged() {
        recreate()
    }

    override fun initData() {
        pkutil = PackageUtil(this)
        dataList.addAll(pkutil.getAppList())
        recyclerView.layoutManager = MyLinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = myAdapter
        myAdapter.notifyDataSetChanged()
        myAdapter.listener = this
    }

    override fun initView() {
        val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE //这个是需要申请的权限信息
        val checkPermission = let { ActivityCompat.checkSelfPermission(this, permission) }
        if (checkPermission == PackageManager.PERMISSION_GRANTED) {//已授权
            L.d("已授权")
        } else {
            ActivityCompat.requestPermissions(this, Array(1) { permission }, 1111)
        }
        toolBar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun onClickRecyclerItem(position: Int) {
        L.d("点击列表项：$position:" + dataList[position].appName)
        pkutil.showCopyApkDialog(this, dataList[position])
    }
}
