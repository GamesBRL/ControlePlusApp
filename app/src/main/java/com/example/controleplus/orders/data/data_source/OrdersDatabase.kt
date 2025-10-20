package com.example.controleplus.orders.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.controleplus.orders.domain.model.Orders

@Database(
    entities = [Orders::class],
    version = 1,
)
@TypeConverters(LocalDateTimeConverter::class)
abstract class OrdersDatabase : RoomDatabase() {

    abstract val ordersDao: OrdersDao

    companion object {
        const val DATABASE_NAME = "orders_db"
    }
}