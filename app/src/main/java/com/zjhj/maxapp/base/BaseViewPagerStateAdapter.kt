package com.zjhj.maxapp.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter


class BaseViewPagerStateAdapter(fm: FragmentManager, val fragmentList: MutableList<Fragment>) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun updateList(fragments: MutableList<Fragment>) {
        this.fragmentList.clear()
        this.fragmentList.addAll(fragments)
        notifyDataSetChanged()
    }

    /**
     * 使用这个方式，让页面不缓存，能够在清除fragment的时候对其做了删除
     *
     * @param object
     * @return
     */
    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE;
    }
}