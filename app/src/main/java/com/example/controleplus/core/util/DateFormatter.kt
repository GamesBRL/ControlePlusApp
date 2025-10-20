package com.example.controleplus.core.util

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import java.util.Locale

object DateFormatter {

    fun formatDate(date: LocalDateTime?): String {
        if (date == null) return ""
        val instant = date.atZone(ZoneId.systemDefault()).toInstant()
        val locale = Locale.getDefault()
        val formatter = DateFormat.getDateInstance(DateFormat.SHORT, locale)
        return formatter.format(Date.from(instant))
    }

    fun formatDateTime(dateTime: LocalDateTime?): String {
        if (dateTime == null) return ""
        val instant = dateTime.atZone(ZoneId.systemDefault()).toInstant()
        val locale = Locale.getDefault()
        val formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale)
        return formatter.format(Date.from(instant))
    }

    fun longToLocalDateTime(timestamp: Long?): LocalDateTime {
        return if (timestamp != null) {
            LocalDateTime.ofInstant(
                Instant.ofEpochMilli(timestamp),
                ZoneId.systemDefault()
            )
        } else {
            LocalDateTime.now()
        }
    }

    fun LocalDateTime.atStartOfDay(): LocalDateTime =
        this.toLocalDate().atStartOfDay()

    fun LocalDateTime.atEndOfDay(): LocalDateTime =
        this.toLocalDate().atTime(23, 59, 59)
}