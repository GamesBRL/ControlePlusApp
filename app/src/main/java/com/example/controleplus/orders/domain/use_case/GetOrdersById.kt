package com.example.controleplus.orders.domain.use_case

import com.example.controleplus.orders.domain.model.Orders
import com.example.controleplus.orders.domain.repository.OrdersRepository

class GetOrdersById(
    private val repository: OrdersRepository
) {

    suspend operator fun invoke(id: Int): Orders?{
        return repository.getOrdersById(id)
    }
}