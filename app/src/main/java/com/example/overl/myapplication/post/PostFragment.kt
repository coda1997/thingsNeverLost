package com.example.overl.myapplication.post

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.overl.myapplication.R
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.startActivity

/**
 * Created by overl on 2018/4/8.
 */
class PostFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        var view = inflater.inflate(R.layout.fragment_post, container, false)
        initView(view)
        return view
    }

    fun initView(view: View?) {
        val postFind = view?.find<Button>(R.id.bt_post_find)
        val postLost = view?.find<Button>(R.id.bt_post_lost)
        postFind?.onClick {
            startActivity<PostFindActivity>()
        }
        postLost?.onClick {
            startActivity<PostLostActivity>()
        }

    }
}