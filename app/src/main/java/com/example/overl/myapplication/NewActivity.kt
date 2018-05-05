package com.example.overl.myapplication

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.view.Window
import android.widget.LinearLayout
import com.example.overl.myapplication.map.MapFragment
import com.example.overl.myapplication.my.MyFragment
import com.example.overl.myapplication.post.PostFragment
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by overl on 2018/4/7.
 */
class NewActivity:FragmentActivity(){
    private var phoneNumber:String=""

    lateinit var mapFragment: MapFragment
    lateinit var postFragment: PostFragment
    lateinit var myFragment: MyFragment
    private lateinit var container:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  phoneNumber=intent.extras.getString("phone")
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_new)
        initFragment()
        supportFragmentManager.selectFragment(postFragment,myFragment,mapFragment)
        find<LinearLayout>(R.id.layout_map).onClick {
            supportFragmentManager.selectFragment(postFragment,myFragment,mapFragment)
        }
        find<LinearLayout>(R.id.layout_post).onClick {
            supportFragmentManager.selectFragment(myFragment,mapFragment,postFragment)
        }
        find<LinearLayout>(R.id.layout_my).onClick {
            supportFragmentManager.selectFragment(mapFragment,postFragment,myFragment)
        }
    }
    private fun initFragment() {
        container=findViewById(R.id.layout_fragment)
        mapFragment= MapFragment(this)
        postFragment= PostFragment()
        myFragment= MyFragment()
        supportFragmentManager.beginTransaction().add(container.id,mapFragment).add(container.id,myFragment).add(container.id,postFragment).commit()

    }
}

private fun FragmentManager.selectFragment(p0:Fragment,p1:Fragment, fragment: Fragment){
    beginTransaction().hide(p0).hide(p1).show(fragment).commit()
}