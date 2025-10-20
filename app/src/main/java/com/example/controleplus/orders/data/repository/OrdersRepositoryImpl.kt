package com.example.controleplus.orders.data.repository

import com.example.controleplus.orders.data.data_source.OrdersDao
import com.example.controleplus.orders.domain.model.Orders
import com.example.controleplus.orders.domain.repository.OrdersRepository
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

class OrdersRepositoryImpl(
    private val dao: OrdersDao
) : OrdersRepository {

    override fun getOrders(): Flow<List<Orders>> {
        return dao.getOrders()
    }

    override fun getIncomeOrders(): Flow<List<Orders>> {
        return dao.getIncomeOrders()
    }

    override fun getTotalIncome(): Flow<Double?> {
        return dao.getTotalIncome()
    }

    override fun getExpenseOrders(): Flow<List<Orders>> {
        return dao.getExpenseOrders()
    }

    override fun getTotalExpense(): Flow<Double?> {
        return dao.getTotalExpense()
    }

    override suspend fun getOrdersById(id: Int): Orders? {
        return dao.getOrdersById(id)
    }

    override fun getOrdersBetweenDates(
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<List<Orders>> {
        return dao.getOrdersBetweenDates(startDate, endDate)
    }

    override fun getIncomeOrdersBetweenDates(
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<List<Orders>> {
        return dao.getIncomeOrdersBetweenDates(startDate, endDate)
    }

    override fun getExpenseOrdersBetweenDates(
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<List<Orders>> {
        return dao.getExpenseOrdersBetweenDates(startDate, endDate)
    }

    override suspend fun insertOrder(order: Orders) {
        dao.insertOrders(order)
    }

    override suspend fun deleteOrder(order: Orders) {
        dao.deleteOrders(order)
    }

}