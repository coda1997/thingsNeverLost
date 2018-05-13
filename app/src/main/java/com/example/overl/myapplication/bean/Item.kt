package com.example.overl.myapplication.bean

import android.app.ActivityManager
import java.util.*

/**
 * Created by overl on 2018/5/12.
 */
data class Item(var id:Int?=null,
                var description:String?=null,
                var title:String?=null,
                var time:Date?=null,
                var picture:Picture?=null,
                var location:Location?=null)
fun item(block:Item.()->Unit)=Item().apply(block)
fun Item.picture(block: Picture.() -> Unit)=Picture().apply(block)
data class Picture(val id:Int?=null,
                   var url:String?=null)