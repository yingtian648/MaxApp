package com.zjhj.maxapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Environment
import android.os.FileUtils
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.webkit.PermissionRequest
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.zjhj.maxapp.base.BaseActivity
import com.zjhj.maxapp.base.BaseRecyclerViewAdapter
import com.zjhj.maxapp.bean.DevInfo
import com.zjhj.maxapp.http.base.BaseRequest
import com.zjhj.maxapp.http.base.IBaseCallView
import com.zjhj.maxapp.myview.MyLinearLayoutManager
import com.zjhj.maxapp.appUtil.AppInfo
import com.zjhj.maxapp.appUtil.PackageUtil
import com.zjhj.maxapp.base.BaseRecyclerViewAdapter.*
import com.zjhj.maxapp.utils.FileUtil
import com.zjhj.maxapp.utils.L
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity(), IBaseCallView, OnClickRecyclerItemListener {
    val req = BaseRequest(this)
    lateinit var pkutil: PackageUtil //延迟初始化，可以避免检查空
    override fun loadStart(msg: String, reqType: Int) {
        L.d(msg)
    }

    override fun loadSuccessData(content: String?, isLoadMore: Boolean, reqType: Int) {
        L.d("返回数据：" + content)
        var info: MutableList<DevInfo>? = req.getResultList(content, DevInfo::class.java)
        if (info != null) {
            L.d(info[0].deviceId + "")
        }
    }

    override fun loadErr(message: String, reqType: Int) {
        L.d("请求失败，" + message)
    }

    val TAG: String = "---------->"
    val handler: Handler = Handler(Looper.getMainLooper())
    var dataList = mutableListOf<AppInfo>()
    var myAdapter = MyAdapter(this, dataList, R.layout.item_rv)

    override fun setContentView() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
    }

    var position = 0

    override fun getData() {
        handler.postDelayed(Runnable {
            runOnUiThread {
                position += 2
                position = if (position >= 10) 0 else position
                recyclerView.layoutManager?.scrollToPosition(position)
                getData()
            }
        }, 6000)
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
//        req?.getData(Urls.getDevEvInfo, 123)//GET Test

//        val params = mutableMapOf<String, Any>()
//        params.put("sn", Urls.SN)
//        params.put("deviceAlarmType", 201)
//        req?.postBody(Urls.devFaultAlarm, Gson().toJson(params), 124)  //POST Test
    }

    override fun onClickRecyclerItem(position: Int) {
        val BACKUP_PATH = "/sdcard/backup1"
        L.d("点击列表项：$position:" + dataList[position].appName)
        val apkFilePath = BACKUP_PATH + pkutil.getApkPath(dataList[position].packageName)
        var savePath = Environment.DIRECTORY_DOWNLOADS + "/111.apk"
        FileUtil.copyFile(apkFilePath, savePath)
    }
}
