package com.sin.sunflowerp.data

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun calendarToDateStamp(calendar:Calendar):Long = calendar.timeInMillis

    @TypeConverter
    fun dateStampToCalendar(timeMills:Long) = Calendar.getInstance().apply {
        timeInMillis = timeMills
    }
}