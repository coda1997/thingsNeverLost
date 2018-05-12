package com.example.overl.myapplication.post

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.overl.myapplication.R
import com.example.overl.myapplication.adapter.ItemAdapter
import com.example.overl.myapplication.bean.Item
import com.example.overl.myapplication.bean.item
import com.example.overl.myapplication.bean.picture
import org.jetbrains.anko.find
import java.util.*

/**
 * Created by overl on 2018/5/12.
 */
class AllItemActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_item)
        val rv = find<RecyclerView>(R.id.all_item_recycle_view)
        val adaper = ItemAdapter(this, initData())
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter=adaper
    }

    private fun initData() :List<Item>{
        val list = mutableListOf<Item>()
        (0 .. 9).forEach {
            list.add(
                    item {
                        id=it
                        description="我奔跑在我孤傲的路上，使然看不见终点和希望，有太多火焰冷却我的理想，我依然燃烧我仍在信仰。"
                        time = Date()
                        location = com.example.overl.myapplication.bean.location {
                            description="school"
                        }
                        picture = picture {
                            url="http://i4.bvimg.com/601522/c1196d7b07148479.jpg"
                        }
                    }
            )
        }
        return list
    }

}