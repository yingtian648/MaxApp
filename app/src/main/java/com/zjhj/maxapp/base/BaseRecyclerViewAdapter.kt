package com.zjhj.maxapp.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * CreateTime 2020/4/2 09:15
 * Author LiuShiHua
 * Description：
 */
abstract class BaseRecyclerViewAdapter<T>(context: Context, dataList: List<T>, layoutId: Int) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var dataList = dataList
    val context = context
    val layoutId = layoutId

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(context).inflate(layoutId, parent, false)
        return onCreateHolder(view)
    }

    abstract fun onCreateHolder(view: View): RecyclerView.ViewHolder

    override fun getItemCount(): Int {
        return dataList.count()
    }
}