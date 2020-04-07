package com.zjhj.maxapp

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zjhj.maxapp.base.BaseRecyclerViewAdapter
import com.zjhj.maxapp.bean.DevsInfo

/**
 * CreateTime 2020/4/2 09:51
 * Author LiuShiHua
 * Description：
 */
class MyAdapter(context: Context, dataList: List<DevsInfo>, layoutId: Int) :
    BaseRecyclerViewAdapter<DevsInfo>(context, dataList, layoutId) {
    override fun onCreateHolder(view: View): RecyclerView.ViewHolder {
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder1 = holder as ItemHolder
        holder1.evOrder.text = dataList[position].evOrder ?: "梯号：--"
        holder1.helpPhone.text = dataList[position].helpPhone ?: "应急救援电话："
        holder1.regCode.text = dataList[position].regCode ?: "注册号码："
        holder1.lastMT.text = dataList[position].lastMT ?: "上次保养时间："
        holder1.nextAT.text = dataList[position].nextAT ?: "下次维保时间："
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        val disp = DisplayMetrics()
        holder.itemView.display.getMetrics(disp)
    }

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        val evOrder = view.findViewById<TextView>(R.id.evOrder)
        val regCode = view.findViewById<TextView>(R.id.regCode)
        val nextAT = view.findViewById<TextView>(R.id.nextAT)
        val lastMT = view.findViewById<TextView>(R.id.lastMT)
        val helpPhone = view.findViewById<TextView>(R.id.helpPhone)
    }
}