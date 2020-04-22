package com.zjhj.maxapp.screensame.localServer

import android.util.Log
import com.google.gson.Gson
import com.koushikdutta.async.http.Multimap
import com.koushikdutta.async.http.body.AsyncHttpRequestBody
import com.koushikdutta.async.http.server.AsyncHttpServer
import com.koushikdutta.async.http.server.AsyncHttpServerRequest
import com.koushikdutta.async.http.server.AsyncHttpServerResponse
import com.koushikdutta.async.http.server.HttpServerRequestCallback
import com.zjhj.maxapp.screensame.util.EventBean
import com.zjhj.maxapp.utils.PathUtil
import com.zjhj.maxapp.utils.Tools
import org.greenrobot.eventbus.EventBus
import org.json.JSONObject
import java.io.File


/**
 * CreateTime 2020/4/21 17:43
 * Author LiuShiHua
 * Description：
 */
class LocalHttpServer private constructor() : HttpServerRequestCallback {

    private val TAG = "----->LocalHttpServer"
    val HTTP_SERVER_PORT = 5051 //服务端口号
    val REPOMSE_SUCCESS_CODE = 0 //请求成功码
    val REPOMSE_ERR_CODE = -1 //请求失败码
    val EVENT_SERVER_STARTED = 4001 //eventbus传递类型【本地服务已开启】

    private val eventBus = EventBus.getDefault()
    var server = AsyncHttpServer()

    companion object {
        private var mInstance: LocalHttpServer? = null

        @Synchronized
        fun getInstance(): LocalHttpServer? {
            if (mInstance == null) { // 增加类锁,保证只初始化一次
                mInstance = LocalHttpServer()
            }
            return mInstance
        }
    }

    /**
     * 开启本地服务
     * @param uri 传递要服务的数据
     * @param needServerType 传递要服务的类型
     */
    fun startServer(uri: String?, needServerType: Int) { //如果有其他的请求方式，例如下面一行代码的写法
        server.addAction("OPTIONS", "[\\d\\D]*", this)
        server["[\\d\\D]*", this]
        server.post("[\\d\\D]*", this)
        server.get("[\\d\\D]*", this)
        server.listen(HTTP_SERVER_PORT)
        eventBus.post(
            EventBean(
                "本地服务已开启",
                "http://" + Tools.getLocalHostIpIPV4() + ":" + HTTP_SERVER_PORT,
                EVENT_SERVER_STARTED
            )
        )
    }

    /**
     * 收到回调此方法
     */
    override fun onRequest(request: AsyncHttpServerRequest?, response: AsyncHttpServerResponse?) {
        //此方法包括了封装返回的接口请求数据和处理异常以及跨域
        response?.headers?.add("Access-Control-Allow-Headers", "allowHeaders")
        response?.headers?.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, HEAD")
        response?.headers?.add("Access-Control-Allow-Credentials", "true")
        response?.headers?.add("Access-Control-Allow-Origin", "*")
        response?.headers?.add("Access-Control-Max-Age", "151200")

        val uri = request!!.path
        Log.d(TAG, "用户请求::" + request.method.toUpperCase() + "::" + uri)

        //获取header参数
        val headers = request.headers.multiMap
        Log.d(TAG, "headers:" + Gson().toJson(headers))
        if (uri != null &&
            (uri.toLowerCase().endsWith(".jpg") ||
                    uri.toLowerCase().endsWith(".png") ||
                    uri.toLowerCase().endsWith(".mp4"))
        ) {//请求文件[图片或mp4]
//            val filePath: String? = getFilePath(uri) // 根据url获取文件路径
            var filePath = PathUtil.getPicTempPath() + File.separator + "a12.jpg"
            if (uri.toLowerCase().endsWith("mp4")) {
                filePath = PathUtil.getPicTempPath() + File.separator + "v13.mp4"
            }
            val file = File(filePath)
            if (file.exists()) {
                Log.d(TAG, "file path = " + file.getAbsolutePath())
                response!!.sendFile(file) //和nanohttpd不一样的地方
            } else {
                Log.d(TAG, "file path = " + file.getAbsolutePath().toString() + "的资源不存在")
                response!!.send("您访问的资源不存在")
            }
        } else { // 针对的是静态资源的处理
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
            response!!.send(getResponseOkObj())
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
    private fun addResponseHeaders(responseHeader: Map<String, Any?>, response: AsyncHttpServerResponse?) {
        if (!responseHeader.isEmpty()) {
            for ((key, value) in responseHeader) {
                response?.headers?.add(key, value?.toString())
            }
        }
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

    //请求成功的返回JSON对象
    private fun getResponseOkObj(): JSONObject {
        return buildResponse(REPOMSE_SUCCESS_CODE, "请求成功", null)
    }

    //请求成功的返回JSON对象
    private fun getResponseOkContentObj(content: String?): JSONObject {
        return buildResponse(REPOMSE_SUCCESS_CODE, "请求成功", content)
    }

    //请求失败的返回JSON对象
    private fun getResponseErrObj(errMsg: String?): JSONObject {
        return buildResponse(REPOMSE_ERR_CODE, "请求失败，" + errMsg, null)
    }

    //构建返回值
    private fun buildResponse(code: Int, message: String, content: String?): JSONObject {
        val map = HashMap<String, Any?>()
        map.put("code", 0)
        map.put("message", "请求成功")
        map.put("content", content)
        return JSONObject(Gson().toJson(map))
    }

    /**
     * 停止服务
     */
    fun stopServer() {
        server.stop()
    }
}