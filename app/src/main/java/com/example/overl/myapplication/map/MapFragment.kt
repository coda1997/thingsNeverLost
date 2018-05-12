package com.example.overl.myapplication.map

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tencent.tencentmap.mapsdk.maps.SupportMapFragment

/**
 * Created by overl on 2018/4/8.
 */
class MapFragment() : SupportMapFragment(), Parcelable {
    @SuppressLint("ValidFragment")
    constructor(parcel: Parcel) : this() {
    }

    override fun onCreateView(layoutinflater: LayoutInflater, viewgroup: ViewGroup?, bundle: Bundle?): View? {
        val view = super.onCreateView(layoutinflater, viewgroup, bundle)
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

