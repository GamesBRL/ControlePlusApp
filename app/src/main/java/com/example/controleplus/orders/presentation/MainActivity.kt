package com.example.controleplus.orders.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.controleplus.core.util.Screen
import com.example.controleplus.orders.presentation.add_orders.AddOrdersScreen
import com.example.controleplus.orders.presentation.home.HomeScreen
import com.example.controleplus.orders.presentation.orders.OrdersScreen
import com.example.controleplus.ui.theme.Black
import com.example.controleplus.ui.theme.ControleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ControleTheme {
                Surface(
                    color = Black
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.HomeScreen.route
                    ) {
                        composable(Screen.HomeScreen.route) {
                            HomeScreen(navController = navController)
                        }
                        composable(Screen.AddOrdersScreen.route) {
                            AddOrdersScreen(navController = navController)
                        }
                        composable(Screen.OrdersScreen.route) {
                            OrdersScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}