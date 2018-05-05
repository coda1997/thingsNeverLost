package com.example.overl.myapplication.service

import com.example.overl.myapplication.bean.ResponseUser
import com.example.overl.myapplication.bean.User
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by overl on 2018/4/7.
 */
interface CreateUser{
    @POST("user/register")
    @FormUrlEncoded
    fun createUser(@Field("phone") phone:String,@Field("password") password:String ): Call<ResponseUser>
    @POST("user/login")
    fun login(@Body userInfo: String):Call<String>

    @POST("item/publishFound")
    fun publishFound(@Body userInfo: String):Call<String>

    @POST("item/publishLost")
    fun publishLost(@Body userInfo: String):Call<String>

    @GET("item/getLost")
    fun getFound():Call<String>
}

