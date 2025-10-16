package com.example.controleplus.di

import android.app.Application
import androidx.room.Room
import com.example.controleplus.feature_orders.data.data_source.OrdersDatabase
import com.example.controleplus.feature_orders.data.repository.OrdersRepositoryImpl
import com.example.controleplus.feature_orders.domain.repository.OrdersRepository
import com.example.controleplus.feature_orders.domain.use_case.AddOrders
import com.example.controleplus.feature_orders.domain.use_case.DeleteOrders
import com.example.controleplus.feature_orders.domain.use_case.GetExpenseOrders
import com.example.controleplus.feature_orders.domain.use_case.GetExpenseOrdersBetweenDates
import com.example.controleplus.feature_orders.domain.use_case.GetExpenseOrdersByDate
import com.example.controleplus.feature_orders.domain.use_case.GetIncomeOrders
import com.example.controleplus.feature_orders.domain.use_case.GetIncomeOrdersBetweenDates
import com.example.controleplus.feature_orders.domain.use_case.GetIncomeOrdersByDate
import com.example.controleplus.feature_orders.domain.use_case.GetOrders
import com.example.controleplus.feature_orders.domain.use_case.GetOrdersBetweenDates
import com.example.controleplus.feature_orders.domain.use_case.GetOrdersByDate
import com.example.controleplus.feature_orders.domain.use_case.GetOrdersById
import com.example.controleplus.feature_orders.domain.use_case.GetTotalExpense
import com.example.controleplus.feature_orders.domain.use_case.GetTotalIncome
import com.example.controleplus.feature_orders.domain.use_case.OrdersUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideOrdersDatabase(app: Application): OrdersDatabase {
        return Room.databaseBuilder(
            app,
            OrdersDatabase::class.java,
            OrdersDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideOrdersRepository(db: OrdersDatabase): OrdersRepository {
        return OrdersRepositoryImpl(db.ordersDao)
    }

    @Provides
    @Singleton
    fun provideOrdersUseCases(repository: OrdersRepository): OrdersUseCases {
        return OrdersUseCases(
            getOrders = GetOrders(repository),
            getIncomeOrders = GetIncomeOrders(repository),
            getTotalIncome = GetTotalIncome(repository),
            getExpenseOrders = GetExpenseOrders(repository),
            getTotalExpense = GetTotalExpense(repository),
            getOrdersById = GetOrdersById(repository),
            getOrdersByDate = GetOrdersByDate(repository),
            getIncomeOrdersByDate = GetIncomeOrdersByDate(repository),
            getExpenseOrdersByDate = GetExpenseOrdersByDate(repository),
            getOrdersBetweenDates = GetOrdersBetweenDates(repository),
            getIncomeOrdersBetweenDates = GetIncomeOrdersBetweenDates(repository),
            getExpenseOrdersBetweenDates = GetExpenseOrdersBetweenDates(repository),
            deleteOrders = DeleteOrders(repository),
            addOrders = AddOrders(repository),
        )
    }
}