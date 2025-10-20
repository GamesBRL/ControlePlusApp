package com.example.controleplus.orders.domain.use_case

import com.example.controleplus.orders.domain.model.InvalidOrdersException
import com.example.controleplus.orders.domain.model.Orders
import com.example.controleplus.orders.domain.repository.OrdersRepository

class AddOrders(
    private val repository: OrdersRepository
) {

    @Throws(InvalidOrdersException::class)
    suspend operator fun invoke(order: Orders) {
        if (order.amount.isNaN()) {
            throw InvalidOrdersException("The amount of the order can't be empty")
        }
        if (order.category.isBlank()) {
            throw InvalidOrdersException("The category of the order can't be empty")
        }
        repository.insertOrder(order)
    }
}