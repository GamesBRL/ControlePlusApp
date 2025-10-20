package com.example.controleplus.orders.domain.repository

import com.example.controleplus.orders.domain.model.Orders
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface OrdersRepository {

    fun getOrders(): Flow<List<Orders>>

    fun getIncomeOrders(): Flow<List<Orders>>

    fun getTotalIncome(): Flow<Double?>

    fun getExpenseOrders(): Flow<List<Orders>>

    fun getTotalExpense(): Flow<Double?>

    suspend fun getOrdersById(id: Int): Orders?

    fun getOrdersBetweenDates(
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<List<Orders>>

    fun getIncomeOrdersBetweenDates(
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<List<Orders>>

    fun getExpenseOrdersBetweenDates(
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<List<Orders>>

    suspend fun insertOrder(order: Orders)

    suspend fun deleteOrder(order: Orders)

}