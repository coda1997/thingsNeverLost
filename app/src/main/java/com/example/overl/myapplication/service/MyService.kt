package com.example.overl.myapplication.service

import com.example.overl.myapplication.bean.Item
import com.example.overl.myapplication.bean.ResponseWithData
import com.example.overl.myapplication.bean.ResponseWithoutData
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by overl on 2018/4/7.
 */
interface MyService{
    @POST("user/register")
    @FormUrlEncoded
    fun createUser(@Field("phone") phone:String,@Field("password") password:String ): Call<ResponseWithoutData>

    @POST("user/login")
    @FormUrlEncoded
    fun login(@Field("phone") phone: String,@Field("password") password: String):Call<String>

    @POST("item/publishFound")
    @FormUrlEncoded
    fun publishFound(@Field("description") description:String,
                     @Field("date") date: String,
                     @Field("user.id") id:Int,
                     @Field("location.latitude") latitude:Double,
                     @Field("location.longitude") longitude:Double,
                     @Field("location.description") localDscrip:String
                     ):Call<ResponseWithoutData>

    @POST
    @FormUrlEncoded
    fun publishLost(
            @Field("description") description:String,
            @Field("date") date: String,
            @Field("user.id") id:Int,
            @Field("location.latitude") latitude:Double,
            @Field("location.longitude") longitude:Double,
            @Field("location.description") localDscrip:String
    ):Call<ResponseWithoutData>

    @POST("item/publishLost")
    @FormUrlEncoded
    fun publishLost(@Field("user") userInfo: String):Call<String>

    @GET("item/getLost")

    fun getFound(): Call<ResponseWithData>
}

