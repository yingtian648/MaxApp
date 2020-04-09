package com.zjhj.maxapp

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zjhj.maxapp.base.BaseRecyclerViewAdapter
import com.zjhj.maxapp.appUtil.AppInfo

/**
 * CreateTime 2020/4/2 09:51
 * Author LiuShiHua
 * Description：
 */
class MyAdapter(context: Context, dataList: List<AppInfo>, layoutId: Int) :
    BaseRecyclerViewAdapter<AppInfo>(context, dataList, layoutId) {
    override fun onCreateHolder(view: View): RecyclerView.ViewHolder {
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder1 = holder as ItemHolder
        holder1.appName.text = dataList[position].appName ?: "应用名称：--"
        holder1.pkName.text = dataList[position].packageName ?: "应用包名：--"
        holder1.versionName.text = "版本名：" + dataList[position].versionName ?: "应用版本名：--"
        holder1.versionCode.text = "版本号：" + dataList[position].versionCode.toString() ?: "应用版本号：--"
        holder1.image.setImageDrawable(dataList[position].iconDrawable)
    }

//    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {//测试用
//        super.onViewAttachedToWindow(holder)
//        val disp = DisplayMetrics()
//        holder.itemView.display.getMetrics(disp)
//    }

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.image)
        val appName = view.findViewById<TextView>(R.id.appName)
        val pkName = view.findViewById<TextView>(R.id.pkName)
        val versionName = view.findViewById<TextView>(R.id.versionName)
        val versionCode = view.findViewById<TextView>(R.id.versionCode)

        init {
            view.setOnClickListener {
                this@MyAdapter.listener?.onClickRecyclerItem(adapterPosition)
            }
        }
    }
}