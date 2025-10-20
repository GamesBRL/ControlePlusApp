package com.example.controleplus.orders.domain.util

sealed class OrderType {

    object Ascending: OrderType()

    object Descending: OrderType()

}