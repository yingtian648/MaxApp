package com.zjhj.maxapp

import android.os.Bundle
import android.text.TextUtils
import com.zjhj.maxapp.base.BaseActivity
import com.zjhj.maxapp.screensame.socket.UDPSocket
import com.zjhj.maxapp.screensame.util.Constants
import com.zjhj.maxapp.screensame.util.EventBean
import com.zjhj.maxapp.utils.Tools
import kotlinx.android.synthetic.main.activity_make_screen_same.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MakeScreenSameActivity : BaseActivity() {
    lateinit var socket: UDPSocket
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setContentView() {
        setContentView(R.layout.activity_make_screen_same)
        EventBus.getDefault().register(this)
    }

    override fun getData() {
    }

    override fun initData() {
        socket = UDPSocket()
        socket.start()
    }

    override fun initView() {
        ipT.setText(Tools.getLocalHostIpIPV4())
        sendMsgBtn.setOnClickListener {
            socket.sendUDPMsg("你好".toByteArray(Charsets.UTF_8),Constants.MSG_TYPE_STRING)
        }
        sendImgMsgBtn.setOnClickListener {
            socket.sendUDPMsg(byteArrayOf(),Constants.MSG_TYPE_IMAGE)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun busReceive(event: EventBean) {
        when (event.type) {
            Constants.EVENT_TYPE_RECEIVE_MSG -> {//收到数据
                msgShow.setText(if (TextUtils.isEmpty(msgShow.text)) (event.msg + "：" + event.content) else (msgShow.text.toString() + "\n" + event.msg + "：" + event.content))
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
