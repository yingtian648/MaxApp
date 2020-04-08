package com.zjhj.maxapp.http.base

import android.os.Build
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.zjhj.maxapp.http.HttpMsgUtil
import com.zjhj.maxapp.http.Urls
import com.zjhj.maxapp.utils.L
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException
import java.util.concurrent.TimeUnit
import okio.Buffer
import java.nio.charset.Charset


/**
 * CreateTime 2020/4/7 09:22
 * Author LiuShiHua
 * Description：
 */
class BaseRequest(val callView: IBaseCallView) {
    companion object {
        val gson = Gson()
        const val SUCCESS_CODE = 200//请求成功码
        const val OPTION_TIME_OUT = 60L//请求超时时长（单位：秒）
    }


    val USER_AGENT = "Android-appV=" + Urls.VERSION + "(sysV-" +
            Build.VERSION.RELEASE + "," + Build.BRAND + "," + Build.MODEL + ")"

    /**
     * 拦截器
     */
    inner class MyInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request.newBuilder()
                .addHeader("user-agent", USER_AGENT) //添加统一header
                .build()
            L.d(request.toString())
            try {
                if (request.method.toUpperCase() == "POST") {
                    val buffer = Buffer()
                    request.body?.writeTo(buffer)
                    val paramsStr = buffer.readString(Charset.forName("UTF-8"))
                    L.d(
                        "mediaType=" + request.body?.contentType()?.type + "/" +
                                request.body?.contentType()?.subtype +
                                "\t" + paramsStr
                    )
                }
            } catch (e: Exception) {
                L.e("MyInterceptor Exception:" + e.message)
            }
            //请求返回值打印
            return chain.proceed(request)
        }
    }

    /**
     * 初始化实例
     * 设置请求超时时长
     */
    val client = OkHttpClient().newBuilder()
        .addInterceptor(MyInterceptor())
        .callTimeout(OPTION_TIME_OUT, TimeUnit.SECONDS)
        .writeTimeout(OPTION_TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(OPTION_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(OPTION_TIME_OUT, TimeUnit.SECONDS)
        .build()


    inner class myBack(val reqType: Int, val isLoadMore: Boolean = false) : Callback {
        override fun onFailure(call: Call, e: IOException) {
            L.e(e.message + "")
            this@BaseRequest.callView.loadErr(HttpMsgUtil.getHttpMsg(e.message), reqType)
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.code == SUCCESS_CODE) {
                var content = response.body?.string()
                L.d(response.code.toString() + ":::" + content)
                this@BaseRequest.callView.loadSuccessData(content, isLoadMore, reqType)
            } else {
                this@BaseRequest.callView.loadErr(HttpMsgUtil.getHttpMsg(response.message), reqType)
            }
        }

    }

    /**
     * GET 方式请求数据
     */
    fun getData(url: String, reqType: Int) {
        val request = Request.Builder()
            .url(url)
            .build()
        callView.loadStart("加载中...", reqType)
        client.newCall(request).enqueue(myBack(reqType))
    }

    /**
     * POST body
     * 参数postBody是JSON字符串
     */
    fun postBody(url: String, postBody: String, reqType: Int) {
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body = postBody.toRequestBody(mediaType)
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()
        callView.loadStart("加载中...", reqType)
        client.newCall(request).enqueue(myBack(reqType))
    }

    /**
     * POST  Map
     * 参数postBody是Map
     */
    fun postBody(url: String, postBody: Map<String, Any>, reqType: Int) {

    }

    /**
     * GET
     * 分页请求
     */
    fun getMoreData(url: String, isLoadMore: Boolean, reqType: Int) {

    }

    fun <T> getResult(content: String?, classType: Class<T>): T? {
        if (content == null || content.equals("") || content.equals(null) || content.equals("unknown"))
            return null
        var t: T? = null
        try {
            t = gson.fromJson(content, classType)
        } catch (e: Exception) {
            e.printStackTrace()
            L.e("Json解析失败")
        }
        return t
    }

    fun <T> getResultList(content: String?, classType: Class<T>): MutableList<T>? {
        if (content == null || content.equals("") || content.equals(null) || content.equals("unknown"))
            return null
        var results = mutableListOf<T>()
        try {
            val Jarray = JsonParser.parseString(content).getAsJsonArray()
            for (obj in Jarray) {
                val member = gson.fromJson(obj, classType)
                results.add(member)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            L.e("Json解析失败")
            return null
        }
        return results
    }
}