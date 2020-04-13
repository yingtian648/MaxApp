package com.zjhj.maxapp

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.projection.MediaProjectionManager
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.RecyclerView
import com.zjhj.maxapp.adapter.ApkCopyAdapter
import com.zjhj.maxapp.adplayer.AdPlayer
import com.zjhj.maxapp.appUtil.AppInfo
import com.zjhj.maxapp.appUtil.PackageUtil
import com.zjhj.maxapp.base.BaseActivity
import com.zjhj.maxapp.base.BaseRecyclerViewAdapter.OnClickRecyclerItemListener
import com.zjhj.maxapp.bean.DevInfo
import com.zjhj.maxapp.http.base.BaseRequest
import com.zjhj.maxapp.http.base.IBaseCallView
import com.zjhj.maxapp.myview.MyLinearLayoutManager
import com.zjhj.maxapp.screensame.Constants
import com.zjhj.maxapp.screensame.RecordScreenService
import com.zjhj.maxapp.utils.L
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), IBaseCallView, OnClickRecyclerItemListener {
    val req = BaseRequest(this)
    lateinit var pkutil: PackageUtil //延迟初始化，可以避免检查空
    lateinit var adContainer: ViewGroup
    lateinit var adPlayer: AdPlayer

    val TAG: String = "---------->"
    val handler: Handler = Handler(Looper.getMainLooper())
    var dataList = mutableListOf<AppInfo>()
    val REQUEST_SYS_SCREENRECORD = 1233
    var myAdapter = ApkCopyAdapter(this, dataList, R.layout.item_rv)

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
        adPlayer = AdPlayer(this, adContainer)
        adPlayer.startPlay()
        dataList.addAll(pkutil.getAppList())
        recyclerView.layoutManager = MyLinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = myAdapter
        myAdapter.notifyDataSetChanged()
        myAdapter.listener = this
    }

    override fun initView() {
        adContainer = findViewById(R.id.adContainer)
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
        L.d("点击列表项：$position:" + dataList[position].appName)
//        pkutil.showCopyApkDialog(this, dataList[position])
        startRecordScreen()
    }

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

    //  调用方式
    lateinit var  mMediaProjectionManager: MediaProjectionManager;

    fun startRecordScreen() {
        L.d("触发录屏服务");
        mMediaProjectionManager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager;
        startActivityForResult(mMediaProjectionManager.createScreenCaptureIntent(),
                REQUEST_SYS_SCREENRECORD);
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_SYS_SCREENRECORD -> {
                if (resultCode == RESULT_OK && data != null) {
                    L.d("启动录屏服务");
                    val service = Intent(MainActivity@this, RecordScreenService::class.java)
                    service.putExtra("code", resultCode);
                    service.putExtra("data", data);
                    service.putExtra(Constants.TYPE_FLAG_NAME, Constants.TYPE_SHOTSCREEN) //[返回]类型-截屏);
                    startService(service)
                }
            }
        }
    }
}
