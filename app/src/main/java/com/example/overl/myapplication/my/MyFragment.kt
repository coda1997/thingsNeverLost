package com.example.overl.myapplication.my

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.overl.myapplication.R
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.CropCircleTransformation
import org.jetbrains.anko.find

/**
 * Created by overl on 2018/4/8.
 */
class MyFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_my, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        val headBlurImage = view?.find<ImageView>(R.id.h_back)
        val headAvaImage = view?.find<ImageView>(R.id.h_head)
        Glide.with(context).load(R.drawable.dog1).bitmapTransform(BlurTransformation(context,25),CenterCrop(context)).into(headBlurImage)
        Glide.with(context).load(R.drawable.dog1).bitmapTransform(CropCircleTransformation(context)).into(headAvaImage)
    }

}