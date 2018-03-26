package com.example.overl.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.button
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast
import org.jetbrains.anko.verticalLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verticalLayout {
            val name = "world"
            button("this is a button"){

            }.onClick {
                toast("hello $name")
            }
        }
    }
}
