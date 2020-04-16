package com.zjhj.maxapp

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import androidx.core.app.ActivityCompat
import com.zjhj.maxapp.base.BaseActivity
import com.zjhj.maxapp.screensame.socket.UDPSocket
import com.zjhj.maxapp.screensame.util.Constants
import com.zjhj.maxapp.screensame.util.EventBean
import com.zjhj.maxapp.utils.L
import com.zjhj.maxapp.utils.Tools
import com.zjhj.maxapp.utils.image.ImageCompressUtil
import com.zjhj.maxapp.utils.image.ImageUtils
import kotlinx.android.synthetic.main.activity_make_screen_same.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.cybergarage.upnp.ControlPoint
import org.cybergarage.upnp.Device
import org.cybergarage.upnp.Service
import org.cybergarage.upnp.device.DeviceChangeListener
import org.cybergarage.upnp.event.EventListener
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.File
import java.net.URL
import java.util.*


class MakeScreenSameActivity : BaseActivity() {
    var img1 = Environment.getExternalStorageDirectory()?.absolutePath + File.separator + "a11.png"
    var img2 = Environment.getExternalStorageDirectory()?.absolutePath + File.separator + "a12.jpg"
    lateinit var socket: UDPSocket
    lateinit var fileNow: File
    lateinit var controlPoint: ControlPoint
    val deviceList = mutableListOf<Device>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        try {
            fileNow = ImageCompressUtil.getCompressFile(img2, 30 * 1024)
        } catch (e: Exception) {
            L.d("压缩失败")
        }
        Timer().schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    image.setImageBitmap(BitmapFactory.decodeFile(fileNow.absolutePath))
                }
            }
        }, 2000)
    }

    override fun initData() {
        socket = UDPSocket()
        socket.start()
    }

    override fun initView() {
        ipT.setText(Tools.getLocalHostIpIPV4())
        sendMsgBtn.setOnClickListener {
            socket.sendUDPMsg("你好".toByteArray(Charsets.UTF_8), Constants.MSG_TYPE_STRING)
        }
        sendImgMsgBtn.setOnClickListener {
            L.d("发送前文件大小：" + fileNow.length())
            socket.sendUDPMsg(
                ImageUtils.getFileBitmapBytesJpg(fileNow.absolutePath),
                Constants.MSG_TYPE_IMAGE
            )
        }
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

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
