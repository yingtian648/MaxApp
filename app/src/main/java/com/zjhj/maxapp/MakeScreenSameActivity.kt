package com.zjhj.maxapp

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import androidx.core.app.ActivityCompat
import com.zjhj.maxapp.base.BaseActivity
import com.zjhj.maxapp.screensame.RecordScreenService
import com.zjhj.maxapp.screensame.udpsocket.UDPSocket
import com.zjhj.maxapp.screensame.util.Constants
import com.zjhj.maxapp.screensame.util.DLanUtil
import com.zjhj.maxapp.screensame.util.EventBean
import com.zjhj.maxapp.screensame.util.EventDevicesBean
import com.zjhj.maxapp.utils.L
import com.zjhj.maxapp.utils.Tools
import com.zjhj.maxapp.utils.image.ImageUtils
import kotlinx.android.synthetic.main.activity_make_screen_same.*
import org.cybergarage.upnp.ControlPoint
import org.cybergarage.upnp.Device
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.File
import java.util.*


class MakeScreenSameActivity : BaseActivity() {
    var img1 = Environment.getExternalStorageDirectory()?.absolutePath + File.separator + "a11.png"
    var img2 = Environment.getExternalStorageDirectory()?.absolutePath + File.separator + "a12.jpg"
    val REQUEST_SYS_SCREENRECORD = 1233
    lateinit var dLanUtil: DLanUtil
    val deviceList = mutableListOf<Device>()

    override fun setContentView() {
        setContentView(R.layout.activity_make_screen_same)
        EventBus.getDefault().register(this)

        val permission = Manifest.permission.WRITE_EXTERNAL_STORAGE //这个是需要申请的权限信息
        val checkPermission = let { ActivityCompat.checkSelfPermission(this, permission) }
        if (checkPermission == PackageManager.PERMISSION_GRANTED) {//已授权
            L.d("已授权")
        } else {
            ActivityCompat.requestPermissions(this, Array(1) { permission }, 1111)
        }
    }

    override fun getData() {

    }

    override fun initData() {
        dLanUtil = DLanUtil(this)
        dLanUtil.startSearchDevices()
    }

    override fun initView() {
        ipT.setText(Tools.getLocalHostIpIPV4())
        sendMsgBtn.setOnClickListener {
            for (dev in deviceList) {
//                if (dev.friendlyName.contains("Windows Media Player")) {
                if (dev.friendlyName.contains("客厅")) {
                    L.d("请求服务设备名："+dev.friendlyName)
                    dLanUtil.reqDlanPlay(dev)
                }
            }
        }
        sendImgMsgBtn.setOnClickListener {

        }
        screenshots.setOnClickListener {
            startPlayViews()
            startRecordScreen()
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
                msgShow.setText(if (TextUtils.isEmpty(msgShow.text)) ("我：" + event.msg) else (msgShow.text.toString() + "\n我：" + event.msg))
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
}
