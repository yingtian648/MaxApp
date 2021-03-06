package com.zjhj.maxapp

import android.Manifest
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.media.projection.MediaProjectionManager
import android.os.IBinder
import android.text.TextUtils
import androidx.core.app.ActivityCompat
import com.zjhj.maxapp.base.BaseActivity
import com.zjhj.maxapp.screensame.RecordScreenService
import com.zjhj.maxapp.screensame.dlan.DLanUtil
import com.zjhj.maxapp.screensame.dlan.DlanController
import com.zjhj.maxapp.screensame.httpserver.HttpServerService
import com.zjhj.maxapp.screensame.util.*
import com.zjhj.maxapp.utils.L
import com.zjhj.maxapp.utils.Tools
import com.zjhj.maxapp.utils.image.ImageUtils
import kotlinx.android.synthetic.main.activity_make_screen_same.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.cybergarage.upnp.Device
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*
import android.net.wifi.WifiManager;
import com.zjhj.maxapp.app_protect.JobSchedulerService
import kotlinx.android.synthetic.main.activity_main.*


class MakeScreenSameActivity : BaseActivity() {
    val controller = DlanController()
    val REQUEST_SYS_SCREENRECORD = 1233
    var currDev: Device = Device()
    lateinit var dLanUtil: DLanUtil
    val deviceList = mutableListOf<Device>()

    override fun setContentView() {
        setContentView(R.layout.activity_make_screen_same)
        EventBus.getDefault().register(this)
    }

    override fun getData() {
//        val intent = Intent("android.settings.WIFI_DISPLAY_SETTINGS");
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        startActivity(intent)
    }

    override fun initData() {
        dLanUtil = DLanUtil(this)
        dLanUtil.startSearchDevices()
        val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE //这个是需要申请的权限信息
        val checkPermission = let { ActivityCompat.checkSelfPermission(this, permission) }
        if (checkPermission == PackageManager.PERMISSION_GRANTED) {//已授权
            L.d("已授权")
        } else {
            ActivityCompat.requestPermissions(this, Array(1) { permission }, 1111)
        }
    }

    override fun initView() {
        ipT.setText(Tools.getLocalHostIpIPV4())

//        val currentURI = "https://media.w3.org/2010/05/sintel/trailer.mp4"
//        var currentURI = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
        var currentURI = "http://192.168.50.227:5051/data/111"

        sendMsgBtn.setOnClickListener {
            GlobalScope.launch {
                for (dev in deviceList) {
                    if (dev.friendlyName.contains("Windows Media Player")) {
                        L.d("请求服务设备名：" + dev.friendlyName)
                        currDev = dev
                        for (serv in dev.serviceList) {
                            if (serv != null) {
                                L.d(serv.toString())
                            }
                        }
                        val playResult = controller.play(dev, currentURI)//播放视频
//                        val playResult = controller.setMute(dev, currentURI)
                        L.d("播放 " + (if (playResult) "成功" else "失败"))
                        L.d("传输状态：" + (controller.getTransportState(dev)))
                    }
                }
            }
        }
        sendImgMsgBtn.setOnClickListener {
            GlobalScope.launch {
                controller.pause(currDev)
            }
        }
        screenshots.setOnClickListener {
            //            startPlayViews()
            startRecordScreen()
//            GlobalScope.launch {
//                controller.continuePlay(currDev)
//            }
        }
        startServer.setOnClickListener {
            //            LocalHttpServer.getInstance()?.startServer("11112.jpg",1)
            bindService(Intent(MainActivity@ this, HttpServerService::class.java), object : ServiceConnection {
                override fun onServiceDisconnected(p0: ComponentName?) {
                    L.d("绑定服务成功")
                }

                override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
                    L.d("解除绑定服务")
                }
            }, BIND_AUTO_CREATE)
        }
        stopServer.setOnClickListener {
            //            LocalHttpServer.getInstance()?.stopServer()
        }
    }

    fun startPlayViews() {
        var imgIndex = 1
        image.setImageResource(R.drawable.a1)
        Timer().schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    when (imgIndex) {
                        1 -> {
                            image.setImageResource(R.drawable.a1)
                            imgIndex = 2
                        }
                        2 -> {
                            image.setImageResource(R.drawable.a2)
                            imgIndex = 3
                        }
                        3 -> {
                            image.setImageResource(R.drawable.a3)
                            imgIndex = 4
                        }
                        4 -> {
                            image.setImageResource(R.drawable.a4)
                            imgIndex = 1
                        }
                    }
                }
            }
        }, 3000, 3000)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun busReceive(event: EventBean) {
        when (event.type) {
            Constants.EVENT_TYPE_RECEIVE_MSG_STRING -> {//收到数据
                msgShow.setText(if (TextUtils.isEmpty(msgShow.text)) (event.msg + "：" + event.content) else (msgShow.text.toString() + "\n" + event.msg + "：" + event.content))
            }
            Constants.EVENT_TYPE_RECEIVE_MSG_IMAGE -> {//收到图片
                var receiveBitmap = ImageUtils.getBitmapFromByteArray(event.bytesContent)
                image.setImageBitmap(receiveBitmap)
                msgShow.setText(if (TextUtils.isEmpty(msgShow.text)) (event.msg + "：" + event.content) else (msgShow.text.toString() + "\n" + event.msg + "：图片"))
            }
            Constants.EVENT_TYPE_SEND_MSG -> {//发送数据
//                msgShow.setText(if (TextUtils.isEmpty(msgShow.text)) ("我：" + event.msg) else (msgShow.text.toString() + "\n我：" + event.msg))
            }
            Constants.EVENT_SERVER_STARTED -> {//本地服务开启
                L.d(event.msg)
                L.d(event.content)
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun busReceiveDevices(event: EventDevicesBean) {
        if (event.device != null)
            deviceList.add(event.device)

        L.d("")
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
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

    }
}
