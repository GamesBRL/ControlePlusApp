package com.example.controleplus.core.util

sealed class Screen(val route: String) {

    object HomeScreen: Screen("home_screen")
    object AddOrdersScreen: Screen("add_orders_screen")
    object OrdersScreen: Screen("orders_screen")
}