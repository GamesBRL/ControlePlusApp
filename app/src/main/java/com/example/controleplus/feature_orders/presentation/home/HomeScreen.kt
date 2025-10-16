package com.example.controleplus.feature_orders.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.controleplus.R
import com.example.controleplus.core.components.BottomNavigationBar
import com.example.controleplus.core.util.CurrencyUtils
import com.example.controleplus.core.util.Screen
import com.example.controleplus.core.util.bottomNavItems
import com.example.controleplus.feature_orders.presentation.home.components.TotalIncomeExpenseBox
import com.example.controleplus.ui.theme.Black
import com.example.controleplus.ui.theme.DarkGray
import com.example.controleplus.ui.theme.White2

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val value = viewModel.state.value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddOrdersScreen.route)
                },
                shape = CircleShape,
                containerColor = DarkGray,
                modifier = Modifier.size(65.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Order",
                    tint = Color.White,
                    modifier = Modifier.size(60.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
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
                .padding(contentPadding)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.title_homescreen),
                color = White2,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(200.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                TotalIncomeExpenseBox(
                    "balance",
                    value.totalBalance,
                    modifier = Modifier.size(135.dp)
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                TotalIncomeExpenseBox(
                    "income",
                    value.totalIncome,
                    modifier = Modifier.size(135.dp)
                )
                TotalIncomeExpenseBox(
                    "expense",
                    value.totalExpense,
                    modifier = Modifier.size(135.dp)
                )
            }
        }
    }
}