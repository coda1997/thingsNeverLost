package com.example.overl.myapplication.bean

/**
 * Created by overl on 2018/5/5.
 */
data class Location(var id:Int=0, var latitude:Double=0.0, var longitude:Double=0.0, var description:String="")
fun location(block:Location.()->Unit)=Location().apply(block)