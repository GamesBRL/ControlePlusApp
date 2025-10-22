package com.example.controleplus.orders.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.controleplus.core.components.BottomNavigationBar
import com.example.controleplus.core.util.bottomNavItems
import com.example.controleplus.orders.presentation.home.components.TotalIncomeExpenseBox
import com.example.controleplus.ui.theme.Black
import com.example.controleplus.ui.theme.DarkGray
import com.example.controleplus.ui.theme.White2

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun HomeScreenPreview() {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                shape = CircleShape,
                containerColor = DarkGray,
                modifier = Modifier.size(65.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar Ordem",
                    tint = White2,
                    modifier = Modifier.size(60.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            val navController = rememberNavController()
            BottomNavigationBar(
                items = bottomNavItems,
                navController = navController,
            )
        }
    ) { contentPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .background(Black)
        ) {
            Text(
                text = "Finance",
                color = Color.White,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 235.dp),
                ) {
                    TotalIncomeExpenseBox(
                        "balance",
                        "0.0",
                        modifier = Modifier.size(135.dp)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 85.dp)
                ) {
                    TotalIncomeExpenseBox(
                        "income",
                        "1000000.0",
                        modifier = Modifier.size(135.dp)
                    )
                    TotalIncomeExpenseBox(
                        "expense",
                        "0.0",
                        modifier = Modifier.size(135.dp)
                    )
                }
            }
        }
    }
}