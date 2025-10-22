package com.example.controleplus.orders.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.controleplus.orders.domain.model.Orders
import kotlinx.coroutines.flow.Flow

@Dao
interface OrdersDao {

    //Query que retorna todas as ORDENS
    @Query("SELECT * FROM orders")
    fun getOrders(): Flow<List<Orders>>

    //Query que retorna todas as ORDENS do TIPO entrada
    @Query("SELECT * FROM orders WHERE type = 'income'")
    fun getIncomeOrders(): Flow<List<Orders>>

    //Query que retorna o valor total de entradas
    @Query("SELECT SUM(amount) FROM orders WHERE type = 'income'")
    fun getTotalIncome(): Flow<Double?>

    //Query que retorna todas as ORDENS do TIPO gasto
    @Query("SELECT * FROM orders WHERE type = 'expense'")
    fun getExpenseOrders(): Flow<List<Orders>>

    //Query que retorna o valor total de gastos
    @Query("SELECT SUM(amount) FROM orders WHERE type = 'expense'")
    fun getTotalExpense(): Flow<Double?>

    //Query que retorna uma ORDEM pelo Id
    @Query("SELECT * FROM orders WHERE id = :id")
    suspend fun getOrdersById(id: Int): Orders?

    //Query que retorna ORDENS em um Intervalo de Tempo
    @Query("SELECT * FROM orders WHERE date BETWEEN :startDate AND :endDate")
    fun getOrdersBetweenDates(
        startDate: Long,
        endDate: Long
    ): Flow<List<Orders>>

    //Query que retorna ORDENS do TIPO entrada em um Intervalo de Tempo
    @Query("SELECT * FROM orders WHERE type = 'income' AND date BETWEEN :startDate AND :endDate")
    fun getIncomeOrdersBetweenDates(
        startDate: Long,
        endDate: Long
    ): Flow<List<Orders>>

    //Query que retorna ORDENS do TIPO gasto em um Intervalo de Tempo
    @Query("SELECT * FROM orders WHERE type = 'expense' AND date BETWEEN :startDate AND :endDate")
    fun getExpenseOrdersBetweenDates(
        startDate: Long,
        endDate: Long
    ): Flow<List<Orders>>

    //Função de Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrders(orders: Orders)

    //Função de Delete
    @Delete
    suspend fun deleteOrders(orders: Orders)

}