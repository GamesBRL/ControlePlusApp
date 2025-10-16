package com.example.controleplus.feature_orders.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.controleplus.feature_orders.domain.model.Orders
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface OrdersDao {

    @Query("SELECT * FROM orders")
    fun getOrders(): Flow<List<Orders>>

    @Query("SELECT * FROM orders WHERE type = 'income'")
    fun getIncomeOrders(): Flow<List<Orders>>

    @Query("SELECT SUM(amount) FROM orders WHERE type = 'income'")
    fun getTotalIncome(): Flow<Double?>

    @Query("SELECT * FROM orders WHERE type = 'expense'")
    fun getExpenseOrders(): Flow<List<Orders>>

    @Query("SELECT SUM(amount) FROM orders WHERE type = 'expense'")
    fun getTotalExpense(): Flow<Double?>

    @Query("SELECT * FROM orders WHERE id = :id")
    suspend fun getOrdersById(id: Int): Orders?

    @Query("SELECT * FROM orders WHERE date = :date")
    fun getOrdersByDate(date: LocalDateTime): Flow<List<Orders>>

    @Query("SELECT * FROM orders WHERE type = 'income' AND date = :date")
    fun getIncomeOrdersByDate(date: LocalDateTime): Flow<List<Orders>>

    @Query("SELECT * FROM orders WHERE type = 'expense' AND date = :date")
    fun getExpenseOrdersByDate(date: LocalDateTime): Flow<List<Orders>>

    @Query("SELECT * FROM orders WHERE date BETWEEN :startDate AND :endDate")
    fun getOrdersBetweenDates(
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<List<Orders>>

    @Query("SELECT * FROM orders WHERE type = 'income' AND date BETWEEN :startDate AND :endDate")
    fun getIncomeOrdersBetweenDates(
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<List<Orders>>

    @Query("SELECT * FROM orders WHERE type = 'expense' AND date BETWEEN :startDate AND :endDate")
    fun getExpenseOrdersBetweenDates(
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<List<Orders>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrders(orders: Orders)

    @Delete
    suspend fun deleteOrders(orders: Orders)

}