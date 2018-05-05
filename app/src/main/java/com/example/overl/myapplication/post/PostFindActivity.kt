package com.example.overl.myapplication.post

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import com.example.overl.myapplication.R
import com.example.overl.myapplication.bean.Location
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

/**
 * Created by overl on 2018/5/5.
 */
class PostFindActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_find)
        userid=intent.extras.getInt("userid")
        initView()
    }
    private fun initView() {
        val submit = find<Button>(R.id.bt_post_submit)
        val cancel = find<Button>(R.id.bt_post_cancel)
        val title = find<EditText>(R.id.edit_item_title)
        val description = find<EditText>(R.id.edit_item_description)
        val date = find<DatePicker>(R.id.dp_item_date)
        val time = find<TimePicker>(R.id.tp_item_time)
        val dateAndTime = "${date.year}-${date.month}-${date.dayOfMonth} ${time.hour}:${time.minute}"
        val mockLocation = Location(0.0,0.0,"no way")
        submit.onClick {
            sumbitItem(title.text.toString(),description.text.toString(),dateAndTime,mockLocation)
        }
        cancel.onClick {
            finish()
        }

    }

    var userid = 1
    private fun sumbitItem(title: String, description: String,date:String,loc:Location) {
        ItemUtils.createItemFound(getString(R.string.base_url),title, description,userid,date,loc,this )
    }


}