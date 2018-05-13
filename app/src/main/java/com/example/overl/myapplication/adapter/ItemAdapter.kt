package com.example.overl.myapplication.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.overl.myapplication.R
import com.example.overl.myapplication.bean.Item
import org.jetbrains.anko.find

/**
 * Created by overl on 2018/5/12.
 */
class ItemAdapter(val context: Context,val list: MutableList<Item>) :RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var inflater:LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(inflater.inflate(R.layout.item_cardview,parent,false))
    }
    fun addData(item:Item){
        list.add(item)
        notifyItemInserted(list.size-1)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val h = holder as ItemViewHolder
        val item = list[position]
        Glide.with(context).load(item.picture?.url).placeholder(R.drawable.dog1).to(h.im)
        if (item.description!=null){
            h.content.text=item.description
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    inner class ItemViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
         var im:ImageView = itemView.find(R.id.cv_image)
         var content:TextView = itemView.find(R.id.cv_text)
    }
}

