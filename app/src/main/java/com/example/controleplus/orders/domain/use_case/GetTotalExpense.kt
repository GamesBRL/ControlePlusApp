package com.example.controleplus.orders.domain.use_case

import com.example.controleplus.orders.domain.repository.OrdersRepository
import kotlinx.coroutines.flow.Flow

class GetTotalExpense(
    private val repository: OrdersRepository
) {

    operator fun invoke(): Flow<Double?> {
        return repository.getTotalExpense()
    }
}