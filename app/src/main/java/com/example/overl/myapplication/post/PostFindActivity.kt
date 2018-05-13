package com.example.overl.myapplication.post

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.*
import com.example.overl.myapplication.R
import com.example.overl.myapplication.bean.Location
import com.example.overl.myapplication.bean.location
import com.example.overl.myapplication.map.LocationSourceImpl
import com.tencent.tencentmap.mapsdk.maps.SupportMapFragment
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import java.util.*


class PostFindActivity : AppCompatActivity() {
    private lateinit var loc: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_find)
        userid = intent.extras.getInt("userid")
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        val mapFragment : SupportMapFragment = supportFragmentManager.findFragmentById(R.id.publish_map) as SupportMapFragment
        val map = mapFragment.map
        map.setLocationSource(LocationSourceImpl(this))
        map.setOnMapLongClickListener { latLng ->
            map.addMarker(MarkerOptions(latLng))
            loc = location {
                latitude = latLng.latitude
                longitude = latLng.longitude
            }
        }

        val submit = find<Button>(R.id.bt_post_submit)
        val cancel = find<Button>(R.id.bt_post_cancel)
        val title = find<EditText>(R.id.edit_item_title)
        val description = find<EditText>(R.id.edit_item_description)
        val date = find<TextView>(R.id.tv_date).apply {
            val cal = Calendar.getInstance()
            text = "${cal.get(Calendar.YEAR)}-${cal.get(Calendar.MONTH)}-${cal.get(Calendar.DAY_OF_MONTH)}"

        }
        val time = find<TextView>(R.id.tv_time).apply {
            val time = Calendar.getInstance()
            text = "${time.get(Calendar.HOUR_OF_DAY)}:${time.get(Calendar.MINUTE)}:00"
        }
        val mockLocation = Location( 1,120.0, 120.0, "no way")
        find<LinearLayout>(R.id.input_layout_date).onClick {
            val dialog = DatePickerDialog(this@PostFindActivity, 0, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth -> date.text = "$year-$month-$dayOfMonth" }, 2018, 5, 7)
            dialog.show()
        }
        find<LinearLayout>(R.id.input_layout_time).onClick {
            TimePickerDialog(this@PostFindActivity, 0, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute -> time.text = "$hourOfDay:$minute" }, 12, 0, true).show()
        }

        submit.onClick {
            val dateAndTime = "${date.text} ${time.text}"
            when {
                title.text.toString() == "" -> toast("请输入标题")
                description.text.toString() =="" -> toast("请输入详情")
                else -> sumbitItem(title.text.toString(), description.text.toString(), dateAndTime, mockLocation,0)
            }
        }
        cancel.onClick {
            val dateAndTime = "${date?.text} ${time?.text}"
            when {
                title?.text.toString() == "" -> toast("请输入标题")
                description?.text.toString() =="" -> toast("请输入详情")
                else -> sumbitItem(title?.text.toString(), description?.text.toString(), dateAndTime, mockLocation,1)
            }        }

    }

    private var userid = 1

    private fun sumbitItem(title: String, description: String, date: String, loc: Location,i:Int) {
        if (i==1){
            ItemUtils.createItemFound(getString(R.string.base_url), title, description, userid, date, loc, this)
        }else{
            ItemUtils.createItemLost(getString(R.string.base_url), title, description, userid, date, loc, this)

        }
    }


}