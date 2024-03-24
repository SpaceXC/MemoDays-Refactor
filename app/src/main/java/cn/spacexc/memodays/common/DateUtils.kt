package cn.spacexc.memodays.common

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.minusTimeToDate(another: Long) = ((this - another) / (1000 * 60 * 60 * 24)).toInt()

fun Long.toDateString(): String {
    val date = Date(this)
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormat.format(date)
}