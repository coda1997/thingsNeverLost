package com.example.overl.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.UiThread
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.overl.myapplication.bean.Item
import com.example.overl.myapplication.bean.ResponseWithData
import com.example.overl.myapplication.map.LocationSourceImpl
import com.example.overl.myapplication.service.MyService
import com.tencent.tencentmap.mapsdk.maps.TencentMap
import com.tencent.tencentmap.mapsdk.maps.model.LatLng
import com.tencent.tencentmap.mapsdk.maps.model.Marker
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions
import org.jetbrains.anko.support.v4.UI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapFragment() : com.tencent.tencentmap.mapsdk.maps.SupportMapFragment(), Parcelable {
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
        val retrofit = Retrofit.Builder().baseUrl(resources.getString(R.string.base_url)).addConverterFactory(GsonConverterFactory.create()).build()
        val call = retrofit.create(MyService::class.java).getFound()
        call.enqueue(object : Callback<ResponseWithData> {
            override fun onFailure(call: Call<ResponseWithData>?, t: Throwable?) {
                Log.e("get item", t?.printStackTrace().toString())
            }

            override fun onResponse(call: Call<ResponseWithData>?, response: Response<ResponseWithData>?) {
                Log.d("get item", response?.body().toString())

                val lostItemList = response?.body()?.data
                //initLostItem(lostItemList)
                UI {
                 initLostItem(lostItemList)
                }
                setInfoWindow()
                //set click listener

                map.setOnMarkerClickListener{ m ->
                    if (m.isInfoWindowShown)
                        m.hideInfoWindow()
                    else {
                        m.showInfoWindow()
                    }
                    true
                }
            }
        })
    }

    private fun initLostItem(lostItemList : List<Item>?) {
        var latitude = 30.3141
        var longitude = 114.217
        lostItemList?.filter{ it.location != null }?.forEach{
            val loc = it.location!!
            Log.d("marker", "success")
            val marker = map.addMarker(MarkerOptions(LatLng(latitude,longitude))
                    .title(it.description).snippet(it.description))
            latitude += 0.01
            longitude += 0.01
            Log.d("marker", "success")
        }
    }

    private fun setInfoWindow() {
        var tvTile: TextView
        val infoWindowAdapter : TencentMap.InfoWindowAdapter = object : TencentMap.InfoWindowAdapter {
            override fun getInfoContents(p0: Marker?): View? {
                return null
            }
            override fun getInfoWindow(p0: Marker?): View? {
                val customInfoWindow : LinearLayout = View.inflate(context, R.layout.item_details, null) as LinearLayout
                tvTile = customInfoWindow.findViewById(R.id.item_desc)
                tvTile.text = p0?.snippet
                return customInfoWindow
            }

        }
        map.setInfoWindowAdapter(infoWindowAdapter)
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

