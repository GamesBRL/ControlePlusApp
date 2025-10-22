package com.example.controleplus.core.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Home

//Lista de Itens da barra de navegação inferior
val bottomNavItems = listOf(
    BottomNavigationItem(
        description = "Home",
        route = Screen.HomeScreen.route,
        selectedIcon = Icons.Default.Home,
        unselectedIcon = Icons.Filled.Home
    ),
    BottomNavigationItem(
        description = "Orders",
        route = Screen.OrdersScreen.route,
        selectedIcon = Icons.Default.AccountBalance,
        unselectedIcon = Icons.Filled.AccountBalance
    ),
)