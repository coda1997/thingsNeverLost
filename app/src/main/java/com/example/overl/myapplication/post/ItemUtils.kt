package com.example.overl.myapplication.post

import android.app.Activity
import android.content.Context
import android.util.Log
import com.example.overl.myapplication.bean.Location
import com.example.overl.myapplication.bean.ResponseWithoutData
import com.example.overl.myapplication.bean.User
import com.example.overl.myapplication.service.MyService
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by overl on 2018/5/5.
 */
class ItemUtils{
    companion object {
        fun createItemFound(baseUrl:String,title:String,description:String,userid: Int,time:String,location:Location,context:Context?){
            val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
            val service = retrofit.create(MyService::class.java)
            val call = service.publishFound(description,time,userid,latitude = location.latitude,longitude = location.longitude,localDscrip = location.description)
            call.enqueue(object :Callback<ResponseWithoutData>{
                override fun onFailure(call: Call<ResponseWithoutData>?, t: Throwable?) {
                    Log.d("create","fail"+t.toString())
                 //   context?.toast("fail")
                }

                override fun onResponse(call: Call<ResponseWithoutData>?, response: Response<ResponseWithoutData>?) {
                    Log.d("create","succeed ${response?.body()}")
                }
            })
        }

        fun createItemLost(baseUrl: String,title: String,description: String,userid: Int,time: String,location: Location,context: Context?){
            val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
            val service = retrofit.create(MyService::class.java)
            val call = service.publishLost(description,time,userid,latitude = location.latitude,longitude = location.longitude,localDscrip = location.description)
            call.enqueue(object :Callback<ResponseWithoutData>{
                override fun onFailure(call: Call<ResponseWithoutData>?, t: Throwable?) {
                    Log.d("create","fail"+t.toString())
                 //   context?.toast("fail")
                }
                override fun onResponse(call: Call<ResponseWithoutData>?, response: Response<ResponseWithoutData>?) {
                    Log.d("create","succeed ${response?.body()}")
                }
            })
        }
    }
}