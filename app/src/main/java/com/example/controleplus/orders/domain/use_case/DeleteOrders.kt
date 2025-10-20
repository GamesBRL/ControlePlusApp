package com.example.controleplus.orders.domain.use_case

import com.example.controleplus.orders.domain.model.Orders
import com.example.controleplus.orders.domain.repository.OrdersRepository

class DeleteOrders(
    private val repository: OrdersRepository
) {

    suspend operator fun invoke(orders: Orders) {
        repository.deleteOrder(orders)
    }
}