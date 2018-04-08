package com.example.overl.myapplication

import com.example.overl.myapplication.bean.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by overl on 2018/4/7.
 */
interface CreateUser{
    @POST("register")
    fun createUser(@Body userInfo:String): Call<String>
    @POST("user/login")
    fun login(@Body userInfo: String):Call<String>

    @POST("item/publishFound")
    fun publishFound(@Body userInfo: String):Call<String>

    @POST("item/publishLost")
    fun publishLost(@Body userInfo: String):Call<String>

    @GET("item/getLost")
    fun getFound():Call<String>
}

