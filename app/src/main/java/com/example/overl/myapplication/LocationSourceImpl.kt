package com.example.overl.myapplication

import android.content.Context
import android.location.Location
import android.util.Log
import com.tencent.map.geolocation.TencentLocation
import com.tencent.map.geolocation.TencentLocationListener
import com.tencent.map.geolocation.TencentLocationManager
import com.tencent.map.geolocation.TencentLocationRequest
import com.tencent.tencentmap.mapsdk.maps.LocationSource

class LocationSourceImpl(context : Context):TencentLocationListener, LocationSource {
    private lateinit var changedListener : LocationSource.OnLocationChangedListener
    private val locationManager : TencentLocationManager = TencentLocationManager.getInstance(context)
    private val locationRequest : TencentLocationRequest = TencentLocationRequest.create()

    override fun activate(p0: LocationSource.OnLocationChangedListener) {
        changedListener = p0
        val err = locationManager.requestLocationUpdates(locationRequest, this)
        when (err) {
            1 -> Log.e("active", "设备缺少使用腾讯定位服务需要的基本条件")
            2 -> Log.e("active", "manifest 中配置的 key 不正确")
            3 -> Log.e("active", "自动加载libtencentloc.so失败")
        }
    }

    override fun deactivate() {
        locationManager.removeUpdates(this)
    }

    override fun onLocationChanged(p0: TencentLocation, p1: Int, p2: String) {
        if (p1 == TencentLocation.ERROR_OK) {
            changedListener.let {
                Log.e("mapLocation", "location: " + p0.city + " " + p0.provider)
                val location = Location(p0.provider)
                location.latitude = p0.latitude
                location.longitude = p0.longitude
                location.accuracy = p0.accuracy
                changedListener.onLocationChanged(location)
            }
        }
    }

    override fun onStatusUpdate(p0: String?, p1: Int, p2: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}