package com.zjhj.maxapp.vm

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zjhj.maxapp.bean.DevInfo
import com.zjhj.maxapp.http.Urls
import com.zjhj.maxapp.http.base.BaseRequest
import com.zjhj.maxapp.http.base.IBaseCallView
import com.zjhj.maxapp.utils.L

/**
 * ViewModel层绑定绑定view
 * 需在MainThread中执行操作
 */
class DevInfoVM : ViewModel(), IBaseCallView {
    val handler = Handler(Looper.getMainLooper())
    val req: BaseRequest = BaseRequest(this)
    private val dev: MutableLiveData<DevInfo> by lazy {
        MutableLiveData<DevInfo>().also {
            loadDevInfo()
        }
    }

    fun getDev(): LiveData<DevInfo> {
        return dev
    }

    private fun loadDevInfo() {
        req.getData(url = Urls.getDevEvInfo, reqType = 111)
    }

    override fun loadStart(msg: String, reqType: Int) {

    }

    override fun loadSuccessData(content: String?, isLoadMore: Boolean, reqType: Int) {
        val info = req.getResultList(content, DevInfo::class.java)
        this.dev.value = info?.get(0)
        handler.postDelayed(Runnable {
            L.d("执行延时操作")
            this.dev.value = null;
        },6000)
    }

    override fun loadErr(message: String, reqType: Int) {

    }
}