package com.example.controleplus.orders.presentation.orders.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.controleplus.R
import com.example.controleplus.core.components.BottomNavigationBar
import com.example.controleplus.core.util.CurrencyUtils
import com.example.controleplus.core.util.DateFormatter
import com.example.controleplus.core.util.bottomNavItems
import com.example.controleplus.orders.domain.model.Orders
import com.example.controleplus.ui.theme.Black
import com.example.controleplus.ui.theme.DarkGray
import com.example.controleplus.ui.theme.LightGray
import com.example.controleplus.ui.theme.LightGreen
import com.example.controleplus.ui.theme.Red
import com.example.controleplus.ui.theme.White2
import com.example.controleplus.ui.theme.White50percent
import java.time.LocalDateTime

@Composable
fun OrderItem(
    order: Orders,
    modifier: Modifier = Modifier,
    onDeleteClick: () -> Unit
) {

    var stringIcon: String
    var color: Color
    var typeSymbol: String

    if (order.type == "income") {
        stringIcon = stringResource(R.string.income_singular).first().toString()
        color = LightGreen
        typeSymbol = "+"
    } else {
        stringIcon = stringResource(R.string.expense_singular).first().toString()
        color = Red
        typeSymbol = "-"
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(LightGray)
            .fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(30.dp)
                .height(50.dp)
        ) {
            Text(
                text = stringIcon,
                color = color,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .height(50.dp)
                .width(140.dp)
        ) {
            Text(order.category, color = White2, style = MaterialTheme.typography.bodyLarge)
            Text(
                DateFormatter.formatDateTime(order.date),
                color = White2,
                style = MaterialTheme.typography.bodySmall
            )
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(50.dp)
                .width(120.dp)
        ) {
            Text(
                text = " $typeSymbol ${CurrencyUtils.formatCurrency(order.amount)}",
                color = White2,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
            )
        }
        IconButton(
            onClick = onDeleteClick,
            modifier = Modifier
                .width(60.dp)
                .height(50.dp)
        ) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "Delete",
                tint = White50percent,
            )
        }
    }
}

@Preview
@Composable
fun OrderItemPreview() {

    Scaffold(
        bottomBar = {
            val navController = rememberNavController()
            BottomNavigationBar(
                items = bottomNavItems,
                navController = navController,
            )
        },
    ) { contentPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Black)
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight()
                    .padding(bottom = 15.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(DarkGray)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    OrderItem(
                        Orders(
                            id = 1,
                            amount = 100000.00,
                            category = "sssssssssssssss",
                            type = "income",
                            date = LocalDateTime.now()
                        ),
                        onDeleteClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    OrderItem(
                        Orders(
                            id = 1,
                            amount = 1000.00,
                            category = "ASDSDASDASD",
                            type = "expense",
                            date = LocalDateTime.now()
                        ),
                        onDeleteClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}