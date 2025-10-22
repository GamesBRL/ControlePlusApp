package com.example.controleplus.orders.domain.repository

import com.example.controleplus.orders.domain.model.Orders
import kotlinx.coroutines.flow.Flow

//Interface do Repositorio de ORDENS, com as funções existentes no DAO
interface OrdersRepository {

    fun getOrders(): Flow<List<Orders>>

    fun getIncomeOrders(): Flow<List<Orders>>

    fun getTotalIncome(): Flow<Double?>

    fun getExpenseOrders(): Flow<List<Orders>>

    fun getTotalExpense(): Flow<Double?>

    suspend fun getOrdersById(id: Int): Orders?

    fun getOrdersBetweenDates(
        startDate: Long,
        endDate: Long
    ): Flow<List<Orders>>

    fun getIncomeOrdersBetweenDates(
        startDate: Long,
        endDate: Long
    ): Flow<List<Orders>>

    fun getExpenseOrdersBetweenDates(
        startDate: Long,
        endDate: Long
    ): Flow<List<Orders>>

    suspend fun insertOrder(order: Orders)

    suspend fun deleteOrder(order: Orders)

}