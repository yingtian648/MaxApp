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
import java.util.*
import java.util.TimerTask


/**
 * ViewModel层绑定绑定view
 * 需在MainThread中执行操作
 */
class DevInfoVM : ViewModel(), IBaseCallView {
    val handler = Handler(Looper.getMainLooper())
    val req: BaseRequest = BaseRequest(this)
    val liveData = MutableLiveData<DevInfo>()

    fun getDev(): LiveData<DevInfo> {
        loadDevInfo()
        return liveData
    }

    fun getData(res : String){
        L.d("$res 加载数据，返回null")
        liveData.postValue(null)
    }

    fun getMoreData() {
        L.d("加载更多，返回数据...")
        liveData.postValue(null)
    }

    private fun loadDevInfo() {//请求设备数据
        L.d("请求数据开始..")
        req.getData(url = Urls.getDevEvInfo, reqType = 111)
    }

    override fun loadStart(msg: String, reqType: Int) {

    }

    override fun loadSuccessData(content: String?, isLoadMore: Boolean, reqType: Int) {
        val info: MutableList<DevInfo>? = req.getResultList(content, DevInfo::class.java)
        if (info != null)
            liveData.postValue(info.get(0))
        Timer().schedule(object : TimerTask(){
            override fun run() {
                L.d("执行延时操作 10秒")
                liveData.postValue(null)
            }
        },10000)
    }

    override fun loadErr(message: String, reqType: Int) {

    }
}