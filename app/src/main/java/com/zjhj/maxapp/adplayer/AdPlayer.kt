package com.zjhj.maxapp.adplayer

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.zjhj.maxapp.base.BaseViewPagerStateAdapter

class AdPlayer(context:AppCompatActivity,viewGroup: ViewGroup) {
    val context = context
    val viewPager = ViewPager(context)
    val fgList = mutableListOf<Fragment>()
    val adapter = BaseViewPagerStateAdapter(context.supportFragmentManager,fgList)
    init {
        viewPager.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT)
        viewPager.adapter = adapter
    }

    fun startPlay(){

    }
}