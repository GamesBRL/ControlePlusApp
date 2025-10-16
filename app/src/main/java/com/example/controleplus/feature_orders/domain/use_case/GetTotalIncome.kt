package com.example.controleplus.feature_orders.domain.use_case

import com.example.controleplus.feature_orders.domain.repository.OrdersRepository
import kotlinx.coroutines.flow.Flow

class GetTotalIncome(
    private val repository: OrdersRepository
) {

    operator fun invoke(): Flow<Double?> {
        return repository.getTotalIncome()
    }
}