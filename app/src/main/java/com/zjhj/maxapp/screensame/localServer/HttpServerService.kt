package com.zjhj.maxapp.screensame.localServer

import android.app.IntentService
import android.content.Intent
import com.zjhj.maxapp.screensame.util.EventBean
import com.zjhj.maxapp.screensame.util.EventDevicesBean
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
    }

    override fun onHandleIntent(p0: Intent?) {
        httpServer = LocalHttpServer.getInstance()!!
        httpServer.startServer("", 1)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEventHappen(event: EventBean) {
        if (event.bytesContent != null) {
            httpServer.bitmapData = event.bytesContent
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        httpServer.stopServer()
        EventBus.getDefault().unregister(this)
    }
}