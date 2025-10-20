package com.example.controleplus.orders.presentation.orders

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CleaningServices
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.controleplus.R
import com.example.controleplus.core.components.BottomNavigationBar
import com.example.controleplus.core.util.bottomNavItems
import com.example.controleplus.orders.presentation.orders.components.OrderSection
import com.example.controleplus.orders.presentation.orders.components.TypeSection
import com.example.controleplus.ui.theme.Black
import com.example.controleplus.ui.theme.DarkGray
import com.example.controleplus.ui.theme.LightGray
import com.example.controleplus.ui.theme.LightGray2
import com.example.controleplus.ui.theme.White2

@Preview
@Composable
fun OrdersScreenPreview() {

    val isTypeSectionVisible = false
    val isOrderSectionVisible = false
    var showPicker by remember { mutableStateOf(false) }

    Scaffold(
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
                .background(Black)
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Text(
                "Orders",
                color = White2,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(top = 25.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth(0.40f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(DarkGray)
                            .clickable { TODO() }
                    ) {
                        Text(
                            text = stringResource(R.string.type_word),
                            style = MaterialTheme.typography.bodyLarge,
                            color = White2,
                            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                        )
                        Icon(
                            imageVector = if (isTypeSectionVisible) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                            contentDescription = "Arrow Down",
                            tint = LightGray,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .size(25.dp)
                        )
                    }
                    AnimatedVisibility(
                        visible = isTypeSectionVisible,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        TypeSection(
                            onSelectType = { type ->
                                when (type) {
                                    "all" -> {
                                        TODO()
                                    }

                                    "income" -> {
                                        TODO()
                                    }

                                    "expense" -> {
                                        TODO()
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth(0.75f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(DarkGray)
                            .clickable { TODO() }
                    ) {
                        Text(
                            text = stringResource(R.string.title_orders),
                            style = MaterialTheme.typography.bodyLarge,
                            color = White2,
                            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                        )
                        Icon(
                            imageVector = if (isOrderSectionVisible) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                            contentDescription = "Arrow Down",
                            tint = LightGray,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .size(25.dp)
                        )
                    }
                    AnimatedVisibility(
                        visible = isOrderSectionVisible,
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        OrderSection(
                            onSelectOrder = { order ->
                                when (order) {
                                    "amount" -> {
                                        TODO()
                                    }

                                    "category" -> {
                                        TODO()
                                    }

                                    "type" -> {
                                        TODO()
                                    }

                                    "date" -> {
                                        TODO()
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .fillMaxHeight()
                    .padding(bottom = 10.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(DarkGray)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, start = 10.dp, end = 10.dp),
                ) {
                    Button(
                        colors = ButtonDefaults.buttonColors(LightGray2),
                        onClick = { TODO() }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.Sort,
                            tint = Black,
                            contentDescription = "Sort Button"
                        )
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(LightGray2),
                        onClick = { showPicker = true }
                    ) {
                        Text("Select Date", color = Black)
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            Icons.Default.CalendarMonth,
                            tint = Black,
                            contentDescription = "Calendar Icon"
                        )
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(LightGray2),
                        onClick = { TODO() }
                    )
                    {
                        Icon(
                            Icons.Default.CleaningServices,
                            contentDescription = "Clear Date Filter",
                            tint = Black
                        )
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) { }
            }
        }
    }
}