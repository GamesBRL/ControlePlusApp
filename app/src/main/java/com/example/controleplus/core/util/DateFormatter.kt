package com.example.controleplus.core.util

import android.icu.text.DateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object DateFormatter {

    //Função que formata Timestamp(Long) para String(Data e Hora), utilizada na camada View
    fun formatDateTime(timestamp: Long): String {
        val date = Date(timestamp)
        val locale = Locale.getDefault()
        val formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale)
        return formatter.format(date)
    }

    //Função que corrige o timestamp retornado pelo DatePicker,
    //recriando um calendario no fuso horario local do dispositivo
    fun correctDatePickerDate(timestampUtc: Long): Long {
        val calendarUtc = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendarUtc.timeInMillis = timestampUtc

        // Cria novo calendário no fuso local
        val localCalendar = Calendar.getInstance(TimeZone.getDefault())
        localCalendar.set(Calendar.YEAR, calendarUtc.get(Calendar.YEAR))
        localCalendar.set(Calendar.MONTH, calendarUtc.get(Calendar.MONTH))
        localCalendar.set(Calendar.DAY_OF_MONTH, calendarUtc.get(Calendar.DAY_OF_MONTH))

        // Retorna timestamp local (00h do dia selecionado)
        localCalendar.set(Calendar.HOUR_OF_DAY, 0)
        localCalendar.set(Calendar.MINUTE, 0)
        localCalendar.set(Calendar.SECOND, 0)
        localCalendar.set(Calendar.MILLISECOND, 0)

        return localCalendar.timeInMillis
    }

    //Função que transforma o timestamp no inicio do dia (00:00:00)
    fun atStartOfDay(timestampUtc: Long): Long {
        val localMillis = correctDatePickerDate(timestampUtc)
        val c = Calendar.getInstance()
        c.timeInMillis = localMillis
        c.set(Calendar.HOUR_OF_DAY, 0)
        c.set(Calendar.MINUTE, 0)
        c.set(Calendar.SECOND, 0)
        c.set(Calendar.MILLISECOND, 0)
        return c.timeInMillis
    }

    //Função que transforma o timestamp no final do dia (23:59:59)
    fun atEndOfDay(timestampUtc: Long): Long {
        val localMillis = correctDatePickerDate(timestampUtc)
        val c = Calendar.getInstance()
        c.timeInMillis = localMillis
        c.set(Calendar.HOUR_OF_DAY, 23)
        c.set(Calendar.MINUTE, 59)
        c.set(Calendar.SECOND, 59)
        c.set(Calendar.MILLISECOND, 999)
        return c.timeInMillis
    }

}