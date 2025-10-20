package com.example.controleplus.orders.domain.use_case

import com.example.controleplus.orders.domain.repository.OrdersRepository
import kotlinx.coroutines.flow.Flow

class GetTotalIncome(
    private val repository: OrdersRepository
) {

    operator fun invoke(): Flow<Double?> {
        return repository.getTotalIncome()
    }
}