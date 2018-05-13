package com.example.overl.myapplication.adapter

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by overl on 2018/5/13.
 */
class DateAdapter : JsonDeserializer<Date>{
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Date {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        return dateFormat.parse(json?.asString)
    }
}