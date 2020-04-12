package com.zjhj.maxapp.adplayer

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.zjhj.maxapp.R
import com.zjhj.maxapp.adplayer.fmpage.PagePic
import com.zjhj.maxapp.base.BaseViewPagerStateAdapter
import java.util.*

class AdPlayer(context: AppCompatActivity, viewGroup: ViewGroup) {
    val context = context
    val viewPager = ViewPager(context)
    val fgList = mutableListOf<Fragment>()
    lateinit var fragmentTemp: Fragment
    val adapter = BaseViewPagerStateAdapter(context.supportFragmentManager, fgList)

    init {
        viewPager.id = R.id.viewPager
        viewPager.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        viewPager.adapter = adapter
        viewGroup.addView(viewPager)
    }

    fun startPlay() {
        fgList.add(PagePic(context, 0))
        fgList.add(PagePic(context, 1))
        fgList.add(PagePic(context, 2))
        adapter.notifyDataSetChanged()

        Timer().schedule(object : TimerTask() {
            override fun run() {
                context.runOnUiThread({
                    when(viewPager.currentItem){
                        0 -> viewPager.setCurrentItem(1)
                        1 -> viewPager.setCurrentItem(2)
                        2 -> viewPager.setCurrentItem(0)
                    }
//                    viewPager.setCurrentItem(1)
//                    fragmentTemp = fgList[0]
//                    fgList.removeAt(0)
//                    fgList.add(fragmentTemp)
//                    viewPager.setCurrentItem(0)
//                    adapter.notifyDataSetChanged()
                })
            }
        }, 6000, 6000)
    }
}