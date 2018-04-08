package com.example.overl.myapplication

import com.example.overl.myapplication.bean.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by overl on 2018/4/7.
 */
interface CreateUser{
    @POST("register")
    fun createUser(@Body user: User): Call<String>


}