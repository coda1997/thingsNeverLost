package com.example.overl.myapplication.post

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.example.overl.myapplication.R
import com.example.overl.myapplication.bean.Location
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast
import java.util.*

class PostLostActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_lost)
        userid = intent.extras.getInt("userid")
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
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
            text = "${time.get(Calendar.HOUR_OF_DAY)}:${time.get(Calendar.MINUTE)}"
        }
        val mockLocation = Location(1,0.0, 0.0, "no way")
        find<LinearLayout>(R.id.input_layout_date).onClick {
            val dialog = DatePickerDialog(this@PostLostActivity, 0, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth -> date.text = "$year-$month-$dayOfMonth" }, 2018, 5, 7)
            dialog.show()
        }
        find<LinearLayout>(R.id.input_layout_time).onClick {
            TimePickerDialog(this@PostLostActivity, 0, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute -> time.text = "$hourOfDay:$minute" }, 12, 0, true).show()
        }
        submit.onClick {
            val dateAndTime = "${date.text} ${time.text}"
            when {
                title.text.toString() == "" -> toast("请输入标题")
                description.text.toString() =="" -> toast("请输入详情")
                else -> sumbitItem(title.text.toString(), description.text.toString(), dateAndTime, mockLocation)
            }
        }
        cancel.onClick {
            finish()
        }

    }
    private var userid = 1
    private fun sumbitItem(title: String, description: String, date: String, loc: Location) {
        ItemUtils.createItemLost(getString(R.string.base_url), title, description, userid, date, loc, this)
    }
}