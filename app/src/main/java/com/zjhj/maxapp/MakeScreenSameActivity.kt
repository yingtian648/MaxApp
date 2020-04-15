package com.zjhj.maxapp

import android.os.Bundle
import android.os.Environment
import android.text.TextUtils
import com.zjhj.maxapp.base.BaseActivity
import com.zjhj.maxapp.screensame.socket.UDPSocket
import com.zjhj.maxapp.screensame.util.Constants
import com.zjhj.maxapp.screensame.util.EventBean
import com.zjhj.maxapp.utils.Tools
import com.zjhj.maxapp.utils.L
import com.zjhj.maxapp.utils.image.ImageCompressUtil
import com.zjhj.maxapp.utils.image.ImageUtils
import kotlinx.android.synthetic.main.activity_make_screen_same.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.cybergarage.upnp.ControlPoint
import org.cybergarage.upnp.Device
import org.cybergarage.upnp.device.DeviceChangeListener
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.File


class MakeScreenSameActivity : BaseActivity() {
    var img1 = Environment.getExternalStorageDirectory()?.absolutePath + File.separator + "a11.png"
    var img2 = Environment.getExternalStorageDirectory()?.absolutePath + File.separator + "a12.jpg"
    lateinit var socket: UDPSocket
    lateinit var fileNow: File
    val deviceList = mutableListOf<Device>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setContentView() {
        setContentView(R.layout.activity_make_screen_same)
        EventBus.getDefault().register(this)
    }

    override fun getData() {
        fileNow = ImageCompressUtil.getCompressFile(img2, 60 * 1024)

        val controlPoint = ControlPoint()
        controlPoint.addNotifyListener({
            L.d("搜索到设备地址：" + it.remoteAddress)
        })
        controlPoint.addDeviceChangeListener(object : DeviceChangeListener {
            override fun deviceRemoved(dev: Device?) {
                L.d("设备离线：" + dev?.friendlyName)
                if (dev != null)
                    deviceList.remove(dev)
            }

            override fun deviceAdded(dev: Device?) {
                L.d("设备加入：" + dev?.friendlyName)
                if (dev != null && "urn:schemas-upnp-org:device:MediaRenderer:1".equals(dev.getDeviceType())) {//判断是否为DMR
                    deviceList.add(dev)
                })
                deviceList.add(dev)
            }
        })
        GlobalScope.launch {
            controlPoint.start()
            controlPoint.search()
        }
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
