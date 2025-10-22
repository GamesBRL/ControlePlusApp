package com.example.controleplus.orders.presentation.orders

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
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
import androidx.compose.foundation.lazy.items
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.controleplus.R
import com.example.controleplus.core.components.BottomNavigationBar
import com.example.controleplus.core.util.bottomNavItems
import com.example.controleplus.orders.domain.util.OrdersOrder
import com.example.controleplus.orders.presentation.orders.components.DateSelector
import com.example.controleplus.orders.presentation.orders.components.OrderItem
import com.example.controleplus.orders.presentation.orders.components.OrderSection
import com.example.controleplus.orders.presentation.orders.components.TypeSection
import com.example.controleplus.ui.theme.Black
import com.example.controleplus.ui.theme.DarkGray
import com.example.controleplus.ui.theme.LightGray
import com.example.controleplus.ui.theme.LightGray2
import com.example.controleplus.ui.theme.White2
import kotlinx.coroutines.launch

@Composable
fun OrdersScreen(
    navController: NavController,
    viewModel: OrdersViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val scope = rememberCoroutineScope()

    val deletedOrderMessage = stringResource(R.string.deleted_order)
    val undoMassage = stringResource(R.string.undo)

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavItems,
                navController = navController,
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { contentPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Black)
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Text(
                stringResource(R.string.title_orders),
                color = White2,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
            )
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth(0.40f)
                        .animateContentSize()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(DarkGray)
                            .clickable { viewModel.onEvent(OrdersEvent.ToggleTypeSection) }
                    ) {
                        Text(
                            text = stringResource(R.string.type_word),
                            style = MaterialTheme.typography.bodyLarge,
                            color = White2,
                            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                        )
                        Icon(
                            imageVector = if (state.isTypeSectionVisible) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                            contentDescription = "Arrow Down",
                            tint = LightGray,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .size(20.dp)
                        )
                    }
                    AnimatedVisibility(
                        visible = state.isTypeSectionVisible,
                        enter = fadeIn() + slideInVertically(
                            initialOffsetY = { fullHeight -> -fullHeight }
                        ),
                        exit = fadeOut() + slideOutVertically(
                            targetOffsetY = { fullHeight -> -fullHeight }
                        )
                    ) {
                        TypeSection(
                            modifier = Modifier
                                .fillMaxWidth(),
                            selectedType = state.selectedType,
                            onSelectType = { type ->
                                when (type) {
                                    "all" -> {
                                        viewModel.onEvent(OrdersEvent.LoadAll)
                                    }

                                    "income" -> {
                                        viewModel.onEvent(OrdersEvent.LoadIncome)
                                    }

                                    "expense" -> {
                                        viewModel.onEvent(OrdersEvent.LoadExpense)
                                    }
                                }
                            }
                        )
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .animateContentSize()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(DarkGray)
                            .clickable { viewModel.onEvent(OrdersEvent.ToggleOrderSection) }
                    ) {
                        Text(
                            text = stringResource(R.string.order_word),
                            style = MaterialTheme.typography.bodyLarge,
                            color = White2,
                            modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
                        )
                        Icon(
                            imageVector = if (state.isOrderSectionVisible) Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                            contentDescription = "Arrow Down",
                            tint = LightGray,
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .size(20.dp)
                        )
                    }
                    AnimatedVisibility(
                        visible = state.isOrderSectionVisible,
                        enter = fadeIn() + slideInVertically(
                            initialOffsetY = { fullHeight -> -fullHeight }
                        ),
                        exit = fadeOut() + slideOutVertically(
                            targetOffsetY = { fullHeight -> -fullHeight }
                        )
                    ) {
                        OrderSection(
                            modifier = Modifier
                                .fillMaxWidth(),
                            selectedOrder = state.selectedOrder,
                            onSelectOrder = { order ->
                                when (order) {
                                    "amount" -> {
                                        viewModel.onEvent(
                                            OrdersEvent.Order(
                                                OrdersOrder.Amount(
                                                    state.ordersOrder.orderType
                                                )
                                            )
                                        )
                                    }

                                    "category" -> {
                                        viewModel.onEvent(
                                            OrdersEvent.Order(
                                                OrdersOrder.Category(
                                                    state.ordersOrder.orderType
                                                )
                                            )
                                        )
                                    }

                                    "type" -> {
                                        viewModel.onEvent(
                                            OrdersEvent.Order(
                                                OrdersOrder.Type(
                                                    state.ordersOrder.orderType
                                                )
                                            )
                                        )
                                    }

                                    "date" -> {
                                        viewModel.onEvent(
                                            OrdersEvent.Order(
                                                OrdersOrder.Date(
                                                    state.ordersOrder.orderType
                                                )
                                            )
                                        )
                                    }
                                }
                            },
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
                        onClick = {
                            viewModel.onEvent(OrdersEvent.ToggleOrderType)
                        }
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.Sort,
                            tint = Black,
                            contentDescription = "Sort Button"
                        )
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(LightGray2),
                        onClick = { viewModel.onEvent(OrdersEvent.ToggleDateSection) }
                    ) {
                        Text(stringResource(R.string.select_date), color = Black)
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            Icons.Default.CalendarMonth,
                            tint = Black,
                            contentDescription = "Calendar Icon"
                        )
                    }
                    Button(
                        colors = ButtonDefaults.buttonColors(LightGray2),
                        onClick = { viewModel.onEvent(OrdersEvent.ClearDateFilters) }
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
                ) {
                    items(state.orders) { orders ->
                        OrderItem(
                            order = orders,
                            onDeleteClick = {
                                viewModel.onEvent(OrdersEvent.DeleteOrders(orders))
                                scope.launch {
                                    val result = snackbarHostState.showSnackbar(
                                        message = deletedOrderMessage,
                                        actionLabel = undoMassage
                                    )
                                    if (result == SnackbarResult.ActionPerformed) {
                                        viewModel.onEvent(OrdersEvent.RestoreOrders)
                                    }
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
    if (state.isDateSectionVisible) {
        DateSelector(
            onDateRangeSelected = { (start, end) ->
                if (end != null && start != null) {
                    val endOfDay = end + (24 * 60 * 60 * 1000) - 1

                    viewModel.onEvent(
                        OrdersEvent.LoadBetweenDates(start, endOfDay)
                    )
                } else if (start != null) {
                    viewModel.onEvent(
                        OrdersEvent.LoadByDate(start)
                    )
                }
            },
            onDismiss = { viewModel.onEvent(OrdersEvent.ToggleDateSection) }
        )
    }
}