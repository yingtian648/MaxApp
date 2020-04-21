package com.zjhj.maxapp.screensame.localServer

import android.util.Log
import com.koushikdutta.async.http.Multimap
import com.koushikdutta.async.http.body.AsyncHttpRequestBody
import com.koushikdutta.async.http.server.AsyncHttpServer
import com.koushikdutta.async.http.server.AsyncHttpServerRequest
import com.koushikdutta.async.http.server.AsyncHttpServerResponse
import com.koushikdutta.async.http.server.HttpServerRequestCallback
import java.io.File


/**
 * CreateTime 2020/4/21 17:43
 * Author LiuShiHua
 * Description：
 */
class LocalHttpServer : HttpServerRequestCallback {

    private val TAG = "----->LocalHttpServer"
    private var mInstance: LocalHttpServer? = null
    var PORT_LISTEN_DEFALT = 5000
    var server = AsyncHttpServer()
    fun getInstance(): LocalHttpServer? {
        if (mInstance == null) { // 增加类锁,保证只初始化一次
            synchronized(LocalHttpServer::class.java) {
                if (mInstance == null) {
                    mInstance = LocalHttpServer()
                }
            }
        }
        return mInstance
    }

    /**
     * 开启本地服务
     */
    fun startServer() { //如果有其他的请求方式，例如下面一行代码的写法
        server.addAction("OPTIONS", "[\\d\\D]*", this)
        server["[\\d\\D]*", this]
        server.post("[\\d\\D]*", this)
        server.listen(PORT_LISTEN_DEFALT)
    }

    override fun onRequest(request: AsyncHttpServerRequest?, response: AsyncHttpServerResponse?) {
        Log.d(TAG, "进来了，哈哈")
        val uri = request!!.path
        //这个是获取header参数的地方，一定要谨记哦
        val headers = request.headers.multiMap
        if (checkUri(uri)) { // 针对的是接口的处理
            //注意：这个地方是获取post请求的参数的地方，一定要谨记哦
            val parms = (request.body as AsyncHttpRequestBody<Multimap?>).get()
            if (headers != null) {
                Log.d(TAG, headers.toString())
            }
            if (parms != null) {
                Log.d(TAG, "parms = $parms")
            }
            if (uri.isEmpty()) {
                throw RuntimeException("无法获取请求地址")
            }
            if ("OPTIONS".toLowerCase() == request.method.toLowerCase()) {
                Log.d(TAG, "OPTIONS探测性请求")
                addHeaderResponse(HttpResponseStatus.REQUEST_OK, response)
                return
            }
            return when (uri) {
                "/test" -> {
                    //接口2
                    //此方法包括了封装返回的接口请求数据和处理异常以及跨域
                    response?.headers?.add("Access-Control-Allow-Headers", "allowHeaders")
                    response?.headers?.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, HEAD")
                    response?.headers?.add("Access-Control-Allow-Credentials", "true")
                    response?.headers?.add("Access-Control-Allow-Origin", "*")
                    response?.headers?.add("Access-Control-Max-Age", "151200")
                    Unit
                }
                else -> {
                    addHeaderResponse(HttpResponseStatus.REQUEST_ERROR_API, response)
                }
            }
        } else { // 针对的是静态资源的处理
            val filePath: String? = getFilePath(uri) // 根据url获取文件路径
            if (filePath == null) {
                Log.d(TAG, "sd卡没有找到")
                response!!.send("sd卡没有找到")
                return
            }
            val file = File(filePath)
            if (file.exists()) {
                Log.d(TAG, "file path = " + file.getAbsolutePath())
                response!!.sendFile(file) //和nanohttpd不一样的地方
            } else {
                Log.d(TAG, "file path = " + file.getAbsolutePath().toString() + "的资源不存在")
            }
        }
    }

    /**
     * 校验网络请求uri是否正常
     */
    private fun checkUri(uri: String?): Boolean {
        return true
    }

    /**
     * 添加返回头
     */
    private fun addHeaderResponse(status: HttpResponseStatus, response: AsyncHttpServerResponse?) {
        response?.headers?.add("code",status.requestStatus.toString())
        response?.headers?.add("message",status.description)
    }

    /**
     * 获取文件路径
     * 1.SD卡是否存在
     * 2.文件是否存在
     */
    private fun getFilePath(uri: String?): String? {
        return if (uri == null || !File(uri).exists()) {
            null
        } else {
            uri
        }
    }
}