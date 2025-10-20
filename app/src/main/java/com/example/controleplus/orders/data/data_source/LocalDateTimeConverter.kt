package com.example.controleplus.orders.data.data_source

import androidx.room.TypeConverter
import java.time.LocalDateTime

class LocalDateTimeConverter {
    @TypeConverter
    fun fromDateTime(date: LocalDateTime): String = date.toString()

    @TypeConverter
    fun toDateTime(value: String): LocalDateTime = LocalDateTime.parse(value)
}