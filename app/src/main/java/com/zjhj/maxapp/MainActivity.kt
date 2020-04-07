package com.zjhj.maxapp

import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.recyclerview.widget.RecyclerView
import com.zjhj.maxapp.base.BaseActivity
import com.zjhj.maxapp.bean.DevInfo
import com.zjhj.maxapp.bean.DevsInfo
import com.zjhj.maxapp.http.Urls
import com.zjhj.maxapp.http.base.BaseRequest
import com.zjhj.maxapp.http.base.IBaseCallView
import com.zjhj.maxapp.myview.MyLinearLayoutManager
import com.zjhj.maxapp.utils.L
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), IBaseCallView {
    val req = BaseRequest(this)
    override fun loadStart(msg: String, reqType: Int) {

    }

    override fun loadSuccessData(content: String?, isLoadMore: Boolean, reqType: Int) {
        L.d("返回数据：" + content)
        var info: DevInfo? = req.getResult(content, DevInfo::class.java)
        L.d(info?.deviceId + "")
    }

    override fun loadErr(message: String, reqType: Int) {
        L.d("请求失败，" + message)
    }

    val TAG: String = "---------->"
    val handler: Handler = Handler(Looper.getMainLooper())
    var dataList = mutableListOf<DevsInfo>()
    var myAdapter = MyAdapter(this, dataList, R.layout.item_rv)

    override fun setContentView() {
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
    }

    var position = 0

    override fun getData() {
        handler.postDelayed(Runnable {
            runOnUiThread {
                position += 2
                position = if (position >= 10) 0 else position
                recyclerView.layoutManager?.scrollToPosition(position)
                getData()
            }
        }, 6000)
    }

    override fun initData() {
        for (i in 1..10) {
            val dev = DevsInfo()
            dev.evOrder = "梯号：" + i
            dev.regCode = "注册号码：1546562554545" + i
            dev.lastMT = "上次保养时间：2020-01-02"
            dev.nextAT = "下次年检时间：2020-01-02"
//            dev.helpPhone = "应急救援电话：1354865656"
            dev.helpPhone = null
            dataList.add(dev)
        }
        recyclerView.layoutManager = MyLinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.adapter = myAdapter
        myAdapter.notifyDataSetChanged()
    }

    override fun initView() {
        req?.getData(Urls.getDevEvInfo, 123)
    }
}
