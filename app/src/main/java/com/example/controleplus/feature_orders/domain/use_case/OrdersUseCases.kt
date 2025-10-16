package com.example.controleplus.feature_orders.domain.use_case


data class OrdersUseCases(
    val getOrders: GetOrders,
    val getIncomeOrders: GetIncomeOrders,
    val getTotalIncome: GetTotalIncome,
    val getExpenseOrders: GetExpenseOrders,
    val getTotalExpense: GetTotalExpense,
    val getOrdersById: GetOrdersById,
    val getOrdersByDate: GetOrdersByDate,
    val getIncomeOrdersByDate: GetIncomeOrdersByDate,
    val getExpenseOrdersByDate: GetExpenseOrdersByDate,
    val getOrdersBetweenDates: GetOrdersBetweenDates,
    val getIncomeOrdersBetweenDates: GetIncomeOrdersBetweenDates,
    val getExpenseOrdersBetweenDates: GetExpenseOrdersBetweenDates,
    val addOrders: AddOrders,
    val deleteOrders: DeleteOrders,
)