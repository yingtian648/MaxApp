package com.zjhj.maxapp.adplayer.fmpage

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.zjhj.maxapp.R

class PagePic(val context: Activity, val type: Int) : Fragment() {
    lateinit var image: ImageView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.page_pic, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        image = view.findViewById(R.id.adp_image)
        image.setBackgroundResource(R.drawable.a1)
        initData()
    }

    private fun initData() {
        if (type == 1) {
            image.setBackgroundResource(R.drawable.a2)
        }
        if (type == 2) {
            image.setBackgroundResource(R.drawable.a3)
        }
    }


}