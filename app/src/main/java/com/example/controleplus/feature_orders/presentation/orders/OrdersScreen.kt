package com.example.controleplus.feature_orders.presentation.orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.controleplus.core.components.BottomNavigationBar
import com.example.controleplus.core.util.bottomNavItems
import com.example.controleplus.ui.theme.Black
import com.example.controleplus.ui.theme.White2

@Composable
fun OrdersScreen(
    navController: NavController,
    viewModel: OrdersViewModel = hiltViewModel()
) {

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavItems,
                navController = navController,
            )
        }
    ) { contentPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Black)
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Text("Orders", color = White2, style = MaterialTheme.typography.headlineLarge)
        }
    }
}