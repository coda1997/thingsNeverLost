package com.example.overl.myapplication.register

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import com.example.overl.myapplication.NewActivity
import com.example.overl.myapplication.R
import com.example.overl.myapplication.bean.ResponseUser
import com.example.overl.myapplication.bean.User
import com.example.overl.myapplication.map.JellyInterpolator
import com.example.overl.myapplication.service.CreateUser
import com.mob.MobSDK
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : Activity(), View.OnClickListener {

    private lateinit var mBtnLogin: TextView

    private lateinit var progress: View

    private lateinit var mInputLayout: View

    private lateinit var mName: LinearLayout
    private lateinit var mPsw: LinearLayout
    private lateinit var eName: EditText
    private lateinit var ePwd: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        MobSDK.init(this)
        initView()
    }

    private fun initView() {
        mBtnLogin = findViewById(R.id.main_btn_login)
        progress = findViewById(R.id.layout_progress)
        mInputLayout = findViewById(R.id.input_layout)
        mName = findViewById(R.id.input_layout_name)
        mPsw = findViewById(R.id.input_layout_psw)
        eName = findViewById(R.id.edit_text_name)
        ePwd = findViewById(R.id.edit_view_pwd)
        mBtnLogin.setOnClickListener(this)
    }

    private var counter = 0
    override fun onClick(v: View) {
        val mWidth = mBtnLogin.measuredWidth.toFloat()
        val mHeight = mBtnLogin.measuredHeight.toFloat()

        mName.visibility = View.INVISIBLE
        mPsw.visibility = View.INVISIBLE

        if (counter++ == 0) {

            sendCode(eName.text.toString())
        } else {
            submitCode(eName.text.toString(), ePwd.text.toString())

        }
        //   register(eName.text.toString(),"12345678")
        inputAnimator(mInputLayout, mWidth, mHeight)

    }

    private fun sendCode(phoneNumber: String) {
        SMSSDK.registerEventHandler(object : EventHandler() {
            override fun afterEvent(event: Int, result: Int, data: Any?) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    Log.d("sendCode", "Succeed")
                } else {
                    Log.d("sendCode", "Failed result $result data $data")
                }
            }
        })
        SMSSDK.getVerificationCode("86", phoneNumber)
    }

    private fun submitCode(phoneNumber: String, code: String) {
        SMSSDK.registerEventHandler(object : EventHandler() {
            override fun afterEvent(event: Int, result: Int, data: Any?) {
                if (result == SMSSDK.RESULT_COMPLETE) {
                    Log.d("submit code", "succeed")
                    register(phoneNumber,"12345678")
                } else {
                    Log.d("submit code", "failed result $result data $data")
                }
            }
        })
        SMSSDK.submitVerificationCode("86", phoneNumber, code)
    }
    private fun register( phone:String, pwd:String){
        val retrofit2 = Retrofit.Builder().baseUrl(getString(R.string.base_url)).addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit2?.create(CreateUser::class.java)
        val call = service?.createUser(phone,"123456")
        call?.enqueue(object : Callback<ResponseUser> {
            override fun onFailure(call: Call<ResponseUser>?, t: Throwable?) {
                Log.d("login","fail ${t.toString()}")
            }
            override fun onResponse(call: Call<ResponseUser>?, response: Response<ResponseUser>?) {
                Log.d("login", "succeed ${response.toString()}")
//                startActivity<NewActivity>("user" to (response?.body() as ResponseUser).data)
            }
        })
        startActivity<NewActivity>("user" to phone)
    }

    private fun inputAnimator(view: View?, w: Float, h: Float) {


        val animator = ValueAnimator.ofFloat(0.0f, w)
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            val params = view!!.layoutParams as ViewGroup.MarginLayoutParams
            params.leftMargin = value.toInt()
            params.rightMargin = value.toInt()
            view.layoutParams = params
        }
        val set = AnimatorSet()
        val animator2 = ObjectAnimator.ofFloat(mInputLayout, "scaleX", 1f, 0.5f)
        set.duration = 1000
        set.interpolator = AccelerateDecelerateInterpolator()
        set.playTogether(animator, animator2)
        set.start()
        set.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
                // TODO Auto-generated method stub
            }

            override fun onAnimationEnd(animation: Animator) {

                progress.visibility = View.VISIBLE
                progressAnimator(progress)
                mInputLayout.visibility = View.INVISIBLE

                Handler().postDelayed({ recovery() }, 2000)
            }

            override fun onAnimationCancel(animation: Animator) {
                // TODO Auto-generated method stub
            }
        })

    }

    private fun progressAnimator(view: View?) {
        val animator = PropertyValuesHolder.ofFloat("scaleX", 0.5f, 1f)
        val animator2 = PropertyValuesHolder.ofFloat("scaleY", 0.5f, 1f)
        val animator3 = ObjectAnimator.ofPropertyValuesHolder(view, animator, animator2)
        animator3.duration = 1000
        animator3.interpolator = JellyInterpolator()
        animator3.start()

    }

    private fun recovery() {
        progress.visibility = View.GONE
        mInputLayout.visibility = View.VISIBLE
        mName.visibility = View.VISIBLE
        mPsw.visibility = View.VISIBLE

        val params = mInputLayout.layoutParams as ViewGroup.MarginLayoutParams
        params.leftMargin = 0
        params.rightMargin = 0
        mInputLayout.layoutParams = params


        val animator2 = ObjectAnimator.ofFloat(mInputLayout, "scaleX", 0.5f, 1f)
        animator2.duration = 500
        animator2.interpolator = AccelerateDecelerateInterpolator()
        animator2.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        SMSSDK.unregisterAllEventHandler()
    }
}

