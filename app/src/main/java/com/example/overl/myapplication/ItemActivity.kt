package com.example.overl.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.overl.myapplication.adapter.ItemAdapter
import com.example.overl.myapplication.bean.ResponseWithData
import com.example.overl.myapplication.service.MyService
import org.jetbrains.anko.find
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by overl on 2018/5/12.
 */
class ItemActivity : AppCompatActivity() {
    var key:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_item)
        val rv = find<RecyclerView>(R.id.all_item_recycle_view)
        val adaper = ItemAdapter(this, mutableListOf())
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter=adaper
        key = intent.extras.getString("key")
        Log.d("search",key)
        initData(adaper)
    }

    private fun initData(adapter: ItemAdapter) {
        val retrofit = Retrofit.Builder().baseUrl(resources.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create()).build()
        val call = retrofit.create(MyService::class.java).getFound()
        call.enqueue(object : Callback<ResponseWithData> {

            override fun onResponse(call: Call<ResponseWithData>?, response: Response<ResponseWithData>?) {
                val list = response?.body()?.data
                Log.d("my lost", "${list?.size}")
                list?.filter { it.title?.contains(key)?:true||it.description?.contains(key)?:true }?.forEach {
                    adapter.addData(it)
                }
            }
            override fun onFailure(call: Call<ResponseWithData>?, t: Throwable?) {
                Log.d("my lost", t.toString())
            }
        })
    }

}