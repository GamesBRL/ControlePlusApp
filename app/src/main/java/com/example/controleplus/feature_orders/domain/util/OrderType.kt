package com.example.controleplus.feature_orders.domain.util

sealed class OrderType {

    object Ascending: OrderType()

    object Descending: OrderType()

}