package com.example.overl.myapplication.my

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.example.overl.myapplication.R
import com.example.overl.myapplication.adapter.ItemAdapter
import com.example.overl.myapplication.bean.Item
import com.example.overl.myapplication.bean.ResponseWithData
import com.example.overl.myapplication.bean.item
import com.example.overl.myapplication.service.MyService
import org.jetbrains.anko.find
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

/**
 * Created by overl on 2018/5/12.
 */
class AllItemActivity :AppCompatActivity() {
    var userid:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_item)
        val rv = find<RecyclerView>(R.id.all_item_recycle_view)
        val adaper = ItemAdapter(this, mutableListOf())
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter=adaper
        userid = intent.extras.getInt("userid")
        initData(adaper)
    }

    private fun initData(adapter: ItemAdapter) {
        val retrofit = Retrofit.Builder().baseUrl(resources.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create()).build()
        val call = retrofit.create(MyService::class.java).getFound()
        call.enqueue(object :Callback<ResponseWithData>{

            override fun onResponse(call: Call<ResponseWithData>?, response: Response<ResponseWithData>?) {
                val list = response?.body()?.data
                Log.d("my lost","${list?.size}")
                list?.forEach {
                    adapter.addData(it)
                }
            }
            override fun onFailure(call: Call<ResponseWithData>?, t: Throwable?) {
                Log.d("my lost",t.toString())
            }
        })
    }

}