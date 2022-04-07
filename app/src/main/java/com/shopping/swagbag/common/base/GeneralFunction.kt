package com.shopping.swagbag.common.base

import java.text.SimpleDateFormat
import java.util.*

object GeneralFunction {

    fun getProductQty() = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)


    fun convertServerDateToUserTimeZoneTask(serverDate: String?): String? {
        val ourdate: String
        val serverdateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS"
        val dateFormat = "EEE, d MMM yyyy"
        ourdate = try {
            val formatter = SimpleDateFormat(serverdateFormat)
            formatter.timeZone = TimeZone.getTimeZone("Asia/Kolkata")
            val value: Date = formatter.parse(serverDate)
            val millis: Long = value.time
            val timeZone: TimeZone = TimeZone.getDefault()
            val dateFormatter =
                SimpleDateFormat(dateFormat, Locale.getDefault()) //this format changeable
            dateFormatter.timeZone = timeZone
            dateFormatter.format(value)
            //Log.d("OurDate", OurDate);
        } catch (e: Exception) {
            "0000-00-00 00:00:00"
        }
        return ourdate
    }

}