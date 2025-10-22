package com.example.controleplus.orders.presentation.orders

import com.example.controleplus.orders.domain.model.Orders
import com.example.controleplus.orders.domain.util.OrdersOrder
import java.time.LocalDateTime

sealed class OrdersEvent {

    data class Order(val ordersOrder: OrdersOrder) : OrdersEvent()
    data class DeleteOrders(val orders: Orders) : OrdersEvent()
    data class LoadByDate(val date: Long) : OrdersEvent()
    data class LoadBetweenDates(val startDate: Long, val endDate: Long) :
        OrdersEvent()
    object RestoreOrders : OrdersEvent()
    object ClearDateFilters : OrdersEvent()
    object LoadAll : OrdersEvent()
    object LoadIncome : OrdersEvent()
    object LoadExpense : OrdersEvent()
    object ToggleOrderType : OrdersEvent()
    object ToggleTypeSection : OrdersEvent()
    object ToggleOrderSection : OrdersEvent()
    object ToggleDateSection : OrdersEvent()
}