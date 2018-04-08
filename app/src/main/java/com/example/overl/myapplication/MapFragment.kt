package com.example.overl.myapplication

import android.annotation.SuppressLint
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tencent.tencentmap.mapsdk.maps.SupportMapFragment
import com.tencent.tencentmap.mapsdk.maps.TencentMap
import org.jetbrains.anko.support.v4.find

/**
 * Created by overl on 2018/4/8.
 */
class MapFragment() : SupportMapFragment(), Parcelable {
    @SuppressLint("ValidFragment")
    constructor(parcel: Parcel) : this() {
    }

    override fun onCreateView(layoutinflater: LayoutInflater, viewgroup: ViewGroup?, bundle: Bundle?): View? {
        var view = super.onCreateView(layoutinflater, viewgroup, bundle)
        init()
        return view
    }

    @SuppressLint("ValidFragment")
    constructor(context: Context) : this() {
        initSosoMap(context)
    }
    private fun init() {
        map.setLocationSource(LocationSourceImpl(context!!))
        map.isMyLocationEnabled = true
        val uiSetting = map.uiSettings
        uiSetting.isMyLocationButtonEnabled = true
    }


    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MapFragment> {
        override fun createFromParcel(parcel: Parcel): MapFragment {
            return MapFragment(parcel)
        }

        override fun newArray(size: Int): Array<MapFragment?> {
            return arrayOfNulls(size)
        }
    }


}

