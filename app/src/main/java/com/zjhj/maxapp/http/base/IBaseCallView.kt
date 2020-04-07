package com.zjhj.maxapp.http.base

/**
 * CreateTime 2020/4/7 09:40
 * Author LiuShiHua
 * Description：
 */
interface IBaseCallView {
    fun loadStart(msg: String, reqType: Int)

    fun loadSuccessData(content: String?, isLoadMore: Boolean, reqType: Int)

    fun loadErr(message: String, reqType: Int)
}