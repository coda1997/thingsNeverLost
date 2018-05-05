package com.example.overl.myapplication.bean


data class User(val password:String,val phone:String,val age:Int=0,val pictureUrl:String="")

fun User.loginInfo():String{
    return "password: $password\nphone $phone"
}

data class ResponseWithoutData(val code:Int, val msg:String)