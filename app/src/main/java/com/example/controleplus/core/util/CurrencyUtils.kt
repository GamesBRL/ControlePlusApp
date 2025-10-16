package com.example.controleplus.core.util

import java.text.NumberFormat
import java.text.ParseException
import java.util.Locale

object CurrencyUtils {

    fun formatCurrency(value: Double): String {
        val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
        format.maximumFractionDigits = 2
        return format.format(value)
    }

    fun parseCurrencyToDouble(currency: String): Double {
        return try {
            val format = NumberFormat.getCurrencyInstance(Locale.getDefault())
            val parsed = format.parse(currency.trim())
            parsed?.toDouble() ?: 0.0
        } catch (_: ParseException) {
            0.0
        } catch (_: Exception) {
            0.0
        }
    }
}
