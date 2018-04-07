package com.example.overl.myapplication

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

/**
 * Created by overl on 2018/4/7.
 */
class NewActivity:AppCompatActivity(){
    private var phoneNumber:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        phoneNumber=intent.extras.getString("phone")
        verticalLayout{
            textView("hello main phone $phoneNumber")
        }

    }
}