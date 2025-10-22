package com.example.controleplus.core.util

import android.icu.text.DateFormat
import java.util.Date
import java.util.Locale

object DateFormatter {

    //Função que formata Timestamp(Long) para String(Data), utilizada em OrdersViewModel
    fun formatDate(timestamp: Long): String {
        val date = Date(timestamp)
        val locale = Locale.getDefault()
        val formatter = DateFormat.getDateInstance(DateFormat.SHORT, locale)
        return formatter.format(date)
    }

    //Função que formata Timestamp(Long) para String(Data e Hora), utilizada na camada View
    fun formatDateTime(timestamp: Long): String {
        val date = Date(timestamp)
        val locale = Locale.getDefault()
        val formatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, locale)
        return formatter.format(date)
    }

}