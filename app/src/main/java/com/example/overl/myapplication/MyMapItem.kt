package com.example.overl.myapplication

import com.tencent.tencentmap.mapsdk.maps.model.LatLng
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.ClusterItem

/**
 * Created by Administrator on 2018/5/5.
 */
class MyMapItem(latitude:Double, longitude:Double) : ClusterItem {
    private val mLatLng : LatLng = LatLng(latitude, longitude)

    override fun getPosition(): LatLng {
        return mLatLng
    }
}