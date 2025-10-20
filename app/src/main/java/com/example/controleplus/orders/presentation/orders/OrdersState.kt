package com.example.controleplus.orders.presentation.orders

import com.example.controleplus.orders.domain.model.Orders
import com.example.controleplus.orders.domain.util.OrderType
import com.example.controleplus.orders.domain.util.OrdersOrder

data class OrdersState(
    val orders: List<Orders> = emptyList(),
    val ordersOrder: OrdersOrder = OrdersOrder.Date(OrderType.Descending),
    val selectedType: String = "all",
    val selectedOrder: String = "date",
    val formattedStartDate: String = "",
    val formattedEndDate: String = "",
    val formattedCurrentDate: String = "",
    val isTypeSectionVisible: Boolean = false,
    val isOrderSectionVisible: Boolean = false,
    val isDateSectionVisible: Boolean = false
)