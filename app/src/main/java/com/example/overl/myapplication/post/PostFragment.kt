package com.example.overl.myapplication.post

import android.app.DatePickerDialog
import android.app.FragmentManager
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.overl.myapplication.R
import com.example.overl.myapplication.bean.Location
import com.example.overl.myapplication.map.LocationSourceImpl
import com.tencent.tencentmap.mapsdk.maps.MapView
import com.tencent.tencentmap.mapsdk.maps.SupportMapFragment
import com.tencent.tencentmap.mapsdk.maps.model.LatLng
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import java.util.*

/**
 * Created by overl on 2018/4/8.
 */
class PostFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.activity_post_find, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {

        val submit = view?.find<Button>(R.id.bt_post_submit)
        val cancel = view?.find<Button>(R.id.bt_post_cancel)
        val title = view?.find<EditText>(R.id.edit_item_title)
        val description = view?.find<EditText>(R.id.edit_item_description)
        val date = view?.find<TextView>(R.id.tv_date).apply {
            val cal = Calendar.getInstance()
            this!!.text = "${cal.get(Calendar.YEAR)}-${cal.get(Calendar.MONTH)}-${cal.get(Calendar.DAY_OF_MONTH)}"

        }
        val time = view?.find<TextView>(R.id.tv_time).apply {
            val time = Calendar.getInstance()
            this!!.text = "${time.get(Calendar.HOUR_OF_DAY)}:${time.get(Calendar.MINUTE)}"
        }
        val mockLocation = Location( 1,120.0, 120.0, "no way")
        view?.find<LinearLayout>(R.id.input_layout_date)?.onClick {
            val dialog = DatePickerDialog(activity, 0, DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth -> date?.text = "$year-$month-$dayOfMonth" }, 2018, 5, 7)
            dialog.show()
        }
        view?.find<LinearLayout>(R.id.input_layout_time)?.onClick {
            TimePickerDialog(activity, 0, TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute -> time?.text = "$hourOfDay:$minute" }, 12, 0, true).show()
        }

        submit?.onClick {
            val dateAndTime = "${date?.text} ${time?.text}"
            when {
                title?.text.toString() == "" -> toast("请输入标题")
                description?.text.toString() =="" -> toast("请输入详情")
                else -> sumbitItem(title?.text.toString(), description?.text.toString(), dateAndTime, mockLocation,0)
            }
        }
        cancel?.onClick {
            val dateAndTime = "${date?.text} ${time?.text}"
            when {
                title?.text.toString() == "" -> toast("请输入标题")
                description?.text.toString() =="" -> toast("请输入详情")
                else -> sumbitItem(title?.text.toString(), description?.text.toString(), dateAndTime, mockLocation,1)
            }
        }
    }
    private var userid = 1
    private fun sumbitItem(title: String, description: String, date: String, loc: Location,i:Int) {
        if (i==1){
            ItemUtils.createItemFound(getString(R.string.base_url), title, description, userid, date, loc, context)
        }else{
            ItemUtils.createItemLost(getString(R.string.base_url), title, description, userid, date, loc, context)

        }
    }
}