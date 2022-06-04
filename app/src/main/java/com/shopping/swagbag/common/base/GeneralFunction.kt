package com.shopping.swagbag.common.base

import java.text.SimpleDateFormat
import java.util.*

object GeneralFunction {

    fun getProductQty() = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    fun getSortBY() = arrayOf("Default", "Latest", "Sort forward price low", "Sort forward price high")


    fun convertServerDateToUserTimeZoneTask(
        serverDate: String?,
        serverdateFormat: String = "yyyy-MM-dd'T'HH:mm:ss.SSS",
        ourDateFormat: String = "EEE, d MMM yyyy"
    ): String {
        val ourdate: String = try {
            val formatter = SimpleDateFormat(serverdateFormat)
            formatter.timeZone = TimeZone.getTimeZone("Asia/Kolkata")
            val value: Date = formatter.parse(serverDate)
            value.hours = value.hours+5
            value.minutes = value.minutes+30
            val millis: Long = value.time
            val timeZone: TimeZone = TimeZone.getDefault()
            val dateFormatter =
                SimpleDateFormat(ourDateFormat, Locale.getDefault()) //this format changeable
            dateFormatter.timeZone = timeZone
            dateFormatter.format(value)
            //Log.d("OurDate", OurDate);
        } catch (e: Exception) {
            "0000-00-00 00:00:00"
        }/*
        val serverdateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS"
        val ourDateFormat = "EEE, d MMM yyyy"*/
        return ourdate
    }

}