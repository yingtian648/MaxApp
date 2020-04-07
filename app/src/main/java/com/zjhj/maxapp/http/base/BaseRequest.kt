package com.zjhj.maxapp.http.base

import okhttp3.OkHttpClient
import android.os.Build
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.zjhj.maxapp.http.HttpMsgUtil
import com.zjhj.maxapp.http.Urls
import com.zjhj.maxapp.utils.L
import okhttp3.Call
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import okhttp3.Callback
import java.util.concurrent.TimeUnit


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

    //初始化实例
    val client = OkHttpClient().newBuilder().callTimeout(OPTION_TIME_OUT, TimeUnit.SECONDS)
        .connectTimeout(OPTION_TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(OPTION_TIME_OUT, TimeUnit.SECONDS)
        .build()
    val USER_AGENT = "Android-appV=" + Urls.VERSION + "(sysV-" +
            Build.VERSION.RELEASE + "," + Build.BRAND + "," + Build.MODEL + ")"


    inner class myBack(val reqType: Int, val isLoadMore: Boolean = false) : Callback {
        override fun onFailure(call: Call, e: IOException) {
            L.e(call.request().toString())
            L.e(e.message + "")
            this@BaseRequest.callView.loadErr(HttpMsgUtil.getHttpMsg(e.message), reqType)
        }

        override fun onResponse(call: Call, response: Response) {
            L.d(response.code.toString() + ":::" + call.request().toString())
            if (response.code == SUCCESS_CODE) {
                val content = response.body?.string()
                L.d("response:" + content.toString())
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
            .addHeader("user-agent", USER_AGENT)
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