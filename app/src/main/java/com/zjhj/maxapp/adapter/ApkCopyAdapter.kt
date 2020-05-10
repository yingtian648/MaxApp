package com.zjhj.maxapp.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zjhj.maxapp.R
import com.zjhj.maxapp.base.BaseRecyclerViewAdapter
import com.zjhj.maxapp.appcopy.AppInfo

/**
 * CreateTime 2020/4/2 09:51
 * Author LiuShiHua
 * Description：
 */
class ApkCopyAdapter(context: Context, dataList: List<AppInfo>, layoutId: Int) :
    BaseRecyclerViewAdapter<AppInfo>(context, dataList, layoutId) {
    override fun onCreateHolder(view: View): RecyclerView.ViewHolder {
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holder = holder as ItemHolder
        val appInfo = dataList[position]
        with(appInfo) {//with 方便在模块中使用同一个类的不同属性，不同方法
            holder.appName.text = appName ?: "应用名称：--"
            holder.pkName.text = packageName ?: "应用包名：--"
            holder.versionName.text = "版本名：" + versionName ?: "应用版本名：--"
            holder.versionCode.text = "版本号：" + versionCode.toString() ?: "应用版本号：--"
            holder.image.setImageDrawable(iconDrawable)
        }
    }

    inner class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.image)
        val appName = view.findViewById<TextView>(R.id.appName)
        val pkName = view.findViewById<TextView>(R.id.pkName)
        val versionName = view.findViewById<TextView>(R.id.versionName)
        val versionCode = view.findViewById<TextView>(R.id.versionCode)

        init {
            view.setOnClickListener {
                this@ApkCopyAdapter.listener?.onClickRecyclerItem(adapterPosition)
            }
        }
    }
}