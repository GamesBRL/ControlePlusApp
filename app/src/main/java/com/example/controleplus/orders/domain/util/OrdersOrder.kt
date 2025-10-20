package com.example.controleplus.orders.domain.util

sealed class OrdersOrder(val orderType: OrderType) {

    class Amount(orderType: OrderType) : OrdersOrder(orderType)
    class Category(orderType: OrderType) : OrdersOrder(orderType)
    class Type(orderType: OrderType) : OrdersOrder(orderType)
    class Date(orderType: OrderType) : OrdersOrder(orderType)

}