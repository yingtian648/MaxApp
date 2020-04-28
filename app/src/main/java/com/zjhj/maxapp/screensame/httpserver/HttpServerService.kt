package com.zjhj.maxapp.screensame.httpserver

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.zjhj.maxapp.screensame.util.EventBean
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * CreateTime 2020/4/22 17:56
 * Author LiuShiHua
 * Description：
 */
class HttpServerService : IntentService("HttpServerService") {
    lateinit var httpServer: LocalHttpServer
    override fun onCreate() {
        super.onCreate()
        EventBus.getDefault().register(this)
        httpServer = LocalHttpServer.getInstance()!!
        httpServer.startServer("", 1)
    }

    override fun onHandleIntent(p0: Intent?) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventHappen(event: EventBean) {
        if (event.bytesContent != null) {
            Log.d("----->","收到消息")
            httpServer.bitmapData = event.bytesContent
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("------->","onDestroy")
        httpServer.stopServer()
        EventBus.getDefault().unregister(this)
    }
}