package com.zjhj.maxapp.myview

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * CreateTime 2020/4/3 11:04
 * Author LiuShiHua
 * Description：
 */
class MyLinearLayoutManager(context: Context, orientation: Int) : LinearLayoutManager(context, orientation, false) {

    val MILLISECONDS_PER_INCH = 1f
    val millis_px_scroll = calculateSpeedPerPixel(context.getResources().getDisplayMetrics())

    private fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Int {
        return (MILLISECONDS_PER_INCH / (if (displayMetrics != null) displayMetrics.densityDpi else 1)).toInt()
    }

    override fun smoothScrollToPosition(recyclerView: RecyclerView?, state: RecyclerView.State?, position: Int) {
        super.smoothScrollToPosition(recyclerView, state, position)
    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler?, state: RecyclerView.State?): Int {
        return super.scrollVerticallyBy(millis_px_scroll, recycler, state)
    }
}