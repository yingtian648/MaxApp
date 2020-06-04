package com.zjhj.maxapp

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.TypedArray
import android.media.projection.MediaProjectionManager
import android.os.Handler
import android.os.Looper
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.zjhj.maxapp.adapter.ApkCopyAdapter
import com.zjhj.maxapp.adplayer.AdPlayer
import com.zjhj.maxapp.app_protect.JobSchedulerService
import com.zjhj.maxapp.appcopy.AppInfo
import com.zjhj.maxapp.appcopy.PackageUtil
import com.zjhj.maxapp.base.BaseActivity
import com.zjhj.maxapp.base.BaseRecyclerViewAdapter.OnClickRecyclerItemListener
import com.zjhj.maxapp.bean.DevInfo
import com.zjhj.maxapp.http.base.BaseRequest
import com.zjhj.maxapp.http.base.IBaseCallView
import com.zjhj.maxapp.myview.MyLinearLayoutManager
import com.zjhj.maxapp.screensame.RecordScreenService
import com.zjhj.maxapp.screensame.util.Constants
import com.zjhj.maxapp.utils.L
import com.zjhj.maxapp.vm.DevInfoVM
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.reflect.Method


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
    private lateinit var model: DevInfoVM


    override fun setContentView() {
        model = ViewModelProviders.of(this).get(DevInfoVM::class.java)
        model.getDev().observe(this, Observer<DevInfo> { devInfo ->
            // update UI1
            L.d("更新视图:" + devInfo.values?.mainCompany)
            toolBar.title = devInfo.values?.mainCompany
        })

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolBar)
    }

    override fun onStart() {
        super.onStart()
        L.d("onStart")
    }

    override fun onRestart() {
        super.onRestart()
        L.d("onRestart")
    }

    override fun onResume() {
        super.onResume()
        L.d("onResume")
    }

    override fun onStop() {
        super.onStop()
        L.d("onStop")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id: Int = item.getItemId()
        when (id) {
            R.id.menu_appcopy -> startActivity(Intent(this, ApkCopyActivity::class.java))
            R.id.menu_theme_conf -> startActivity(Intent(this, ThemeActivity::class.java))
            else -> {
            }
        }
        return true
    }

    private fun setIconsVisible(menu: Menu?, flag: Boolean) { //判断menu是否为空
        if (menu != null) {
            try { //如果不为空,就反射拿到menu的setOptionalIconsVisible方法
                val method: Method = menu.javaClass.getDeclaredMethod("setOptionalIconsVisible", java.lang.Boolean.TYPE)
                //暴力访问该方法
                method.setAccessible(true)
                //调用该方法显示icon
                method.invoke(menu, flag)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
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
        startService(Intent(this, JobSchedulerService::class.java))
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
    lateinit var mMediaProjectionManager: MediaProjectionManager;

    fun startRecordScreen() {
        L.d("触发录屏服务");
        mMediaProjectionManager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager;
        startActivityForResult(
            mMediaProjectionManager.createScreenCaptureIntent(),
            REQUEST_SYS_SCREENRECORD
        );
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_SYS_SCREENRECORD -> {
                if (resultCode == RESULT_OK && data != null) {
                    L.d("启动录屏服务");
                    val service = Intent(MainActivity@ this, RecordScreenService::class.java)
                    service.putExtra("code", resultCode);
                    service.putExtra("data", data);
                    service.putExtra(Constants.TYPE_FLAG_NAME, Constants.TYPE_SHOTSCREEN) //[返回]类型-截屏);
                    startService(service)
                }
            }
        }
    }

    override fun notifyThemeChanged() {
        val typedValue = TypedValue()
        //获取当前主题下的titlebar_bg属性值，并赋给typedValue
        theme.resolveAttribute(R.attr.titlebar_bg, typedValue, true)
        //设置属性值【注意：属性是那种类型，设置的时候就用成那种类型】
        toolBar.setBackgroundColor(typedValue.data)
    }
}
