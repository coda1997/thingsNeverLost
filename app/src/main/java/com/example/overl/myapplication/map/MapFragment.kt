package com.example.overl.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.overl.myapplication.bean.Item
import com.example.overl.myapplication.bean.Location
import com.example.overl.myapplication.bean.ResponseWithData
import com.example.overl.myapplication.map.LocationSourceImpl
import com.example.overl.myapplication.service.MyService
import com.tencent.tencentmap.mapsdk.maps.SupportMapFragment
import com.tencent.tencentmap.mapsdk.maps.TencentMap
import com.tencent.tencentmap.mapsdk.maps.model.LatLng
import com.tencent.tencentmap.mapsdk.maps.model.Marker
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.ClusterManager
import com.tencent.tencentmap.mapsdk.vector.utils.clustering.view.DefaultClusterRenderer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MapFragment() : SupportMapFragment(), Parcelable {
    @SuppressLint("ValidFragment")
    constructor(parcel : Parcel) : this() {
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
        val retrofit= Retrofit.Builder().baseUrl(resources.getString(R.string.base_url)).addConverterFactory(GsonConverterFactory.create()).build()
        val call = retrofit.create(MyService::class.java).getFound()
        call.enqueue(object :Callback<ResponseWithData>{
            override fun onFailure(call: Call<ResponseWithData>?, t: Throwable?) {
                Log.e("get item", t?.message)
            }

            override fun onResponse(call: Call<ResponseWithData>?, response: Response<ResponseWithData>?) {
                val item = response!!.body()!!.data[0]
                val location : Location? = item.location
                val latLng = LatLng(location!!.latitude, item.location!!.longitude)
                val marker : Marker = map.addMarker(MarkerOptions(latLng).
                        title(item.description).snippet(location.latitude.toString() + location.longitude))
                var tvTile: TextView
                val infoWindowAdapter : TencentMap.InfoWindowAdapter = object : TencentMap.InfoWindowAdapter {
                    override fun getInfoContents(p0: Marker?): View? {
                        return null
                    }

                    override fun getInfoWindow(p0: Marker?): View? {
                        if (p0 == marker) {
                            val customInfoWindow : LinearLayout = View.inflate(context, R.layout.info_windows, null) as LinearLayout
                            tvTile = customInfoWindow.findViewById(R.id.tv_title)
                            tvTile.text = p0.title
                            return customInfoWindow
                        }
                        return null
                    }
                }

                map.setInfoWindowAdapter(infoWindowAdapter)

                map.setOnMarkerClickListener{ m ->
                    if (m.isInfoWindowShown)
                        m.hideInfoWindow()
                    else {
                        m.showInfoWindow()
                    }
                    true
                }


//                val mClusterManager = ClusterManager<MyMapItem>(context, map)
//                val renderer = DefaultClusterRenderer<MyMapItem>(context, map, mClusterManager)
//                renderer.minClusterSize = 1
//                mClusterManager.renderer = renderer
//                val items = mutableListOf<MyMapItem>()
//                items.add(MyMapItem(39.984059,116.307621))
//                items.add(MyMapItem(39.984059,116.307621))
//                items.add(MyMapItem(39.981954,116.304703))
//                items.add(MyMapItem(39.984355,116.312256))
//                items.add(MyMapItem(39.980442,116.315346))
//                items.add(MyMapItem(39.981527,116.308994))
//                items.add(MyMapItem(39.979751,116.310539))
//                items.add(MyMapItem(39.977252,116.305776))
//                items.add(MyMapItem(39.984026,116.316419))
//                items.add(MyMapItem(39.976956,116.314874))
//                items.add(MyMapItem(39.978501,116.311827))
//                items.add(MyMapItem(39.980277,116.312814))
//                items.add(MyMapItem(39.980236,116.369022))
//                items.add(MyMapItem(39.978838,116.368486))
//                items.add(MyMapItem(39.977161,116.367488))
//                items.add(MyMapItem(39.915398,116.396713))
//                items.add(MyMapItem(39.937645,116.455421))
//                items.add(MyMapItem(39.896304,116.321182))
//                items.add(MyMapItem(31.254487,121.452827))
//                items.add(MyMapItem(31.225133,121.485443))
//                items.add(MyMapItem(31.216912,121.442528))
//                items.add(MyMapItem(31.251552,121.500893))
//                items.add(MyMapItem(31.249204,121.455917))
//                items.add(MyMapItem(22.546885,114.042892))
//                items.add(MyMapItem(22.538086,113.999805))
//                items.add(MyMapItem(22.534756,114.082031))
//                mClusterManager.addItems(items)
//                map.setOnCameraChangeListener(mClusterManager)
            }

        })
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

