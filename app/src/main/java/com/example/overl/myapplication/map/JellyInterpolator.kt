package com.example.overl.myapplication.map

import android.animation.TimeInterpolator

/**
 * Created by overl on 2018/4/7.
 */

internal class JellyInterpolator : TimeInterpolator {
    private val factor: Float = 0.15f

    override fun getInterpolation(input: Float): Float {
        return (Math.pow(2.0, (-10 * input).toDouble()) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1).toFloat()
    }
}
