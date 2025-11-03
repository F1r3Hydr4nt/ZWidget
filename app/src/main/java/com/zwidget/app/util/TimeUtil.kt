package com.zwidget.app.util

import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {

    fun getCurrentTimeForTimeZone(timeZoneId: String): String {
        return try {
            val timeZone = TimeZone.getTimeZone(timeZoneId)
            val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
            dateFormat.timeZone = timeZone
            dateFormat.format(Date())
        } catch (e: Exception) {
            "N/A"
        }
    }

    fun getDateForTimeZone(timeZoneId: String): String {
        return try {
            val timeZone = TimeZone.getTimeZone(timeZoneId)
            val dateFormat = SimpleDateFormat("EEE, MMM d", Locale.getDefault())
            dateFormat.timeZone = timeZone
            dateFormat.format(Date())
        } catch (e: Exception) {
            "N/A"
        }
    }

    fun getTimeDifferenceHours(fromTimeZoneId: String, toTimeZoneId: String): Int {
        val fromTz = TimeZone.getTimeZone(fromTimeZoneId)
        val toTz = TimeZone.getTimeZone(toTimeZoneId)
        val currentTime = System.currentTimeMillis()

        val fromOffset = fromTz.getOffset(currentTime)
        val toOffset = toTz.getOffset(currentTime)

        return ((toOffset - fromOffset) / (1000 * 60 * 60))
    }
}
