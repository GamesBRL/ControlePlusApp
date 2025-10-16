package com.example.controleplus.feature_orders.domain.use_case

import com.example.controleplus.feature_orders.domain.model.Orders
import com.example.controleplus.feature_orders.domain.repository.OrdersRepository
import com.example.controleplus.feature_orders.domain.util.OrderType
import com.example.controleplus.feature_orders.domain.util.OrdersOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime

class GetIncomeOrdersByDate(
    private val repository: OrdersRepository
) {

    operator fun invoke(
        date: LocalDateTime,
        ordersOrder: OrdersOrder = OrdersOrder.Date(OrderType.Descending)
    ): Flow<List<Orders>> {
        return repository.getIncomeOrdersByDate(date).map { orders ->
            when (ordersOrder.orderType) {
                is OrderType.Ascending -> {
                    when (ordersOrder) {
                        is OrdersOrder.Amount -> orders.sortedBy { it.amount }
                        is OrdersOrder.Category -> orders.sortedBy { it.category.lowercase() }
                        is OrdersOrder.Date -> orders.sortedBy { it.date }
                        is OrdersOrder.Type -> orders
                    }
                }

                is OrderType.Descending -> {
                    when (ordersOrder) {
                        is OrdersOrder.Amount -> orders.sortedByDescending { it.amount }
                        is OrdersOrder.Category -> orders.sortedByDescending { it.category.lowercase() }
                        is OrdersOrder.Date -> orders.sortedByDescending { it.date }
                        is OrdersOrder.Type -> orders
                    }
                }
            }
        }
    }
}