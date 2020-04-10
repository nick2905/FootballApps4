package com.dicoding.nicolas.utils

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun changeDateFormat(data1: String?, data2: String?): String {
    val dateFormat: DateFormat? = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
    val simpleFormat = SimpleDateFormat("dd MMMM yyyy hh:mm:ss aa zz")
    val dateForm = "$data1 $data2"
    val date: Date = dateFormat?.parse(dateForm)!!
    simpleFormat.timeZone = TimeZone.getTimeZone("GMT+7")
    return simpleFormat.format(date)
}

