package com.zjhj.maxapp.myview

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.DisplayMetrics
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * CreateTime 2020/4/3 11:04
 * Author LiuShiHua
 * Description：
 */
class MyLinearLayoutManager(context: Context, orientation: Int, reverseLayout: Boolean) :
    LinearLayoutManager(context, orientation, reverseLayout) {

    val MILLISECONDS_PER_INCH = 20f
    val millis_px_scroll = calculateSpeedPerPixel(context.getResources().getDisplayMetrics())

    private fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics?): Int {
        return (MILLISECONDS_PER_INCH / (if (displayMetrics != null) displayMetrics.densityDpi else 1)).toInt()
    }

    fun scrollToPositionN(position: Int) {

    }
}