package com.example.overl.myapplication.map

import com.tencent.tencentmap.mapsdk.maps.model.LatLng
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.ClusterItem

/**
 * Created by Administrator on 2018/5/13.
 */
class MyClusterItem(latitude:Double, longitude:Double) : ClusterItem {
    private val position : LatLng = LatLng(latitude, longitude)

    override fun getPosition(): LatLng {
        return position
    }
}