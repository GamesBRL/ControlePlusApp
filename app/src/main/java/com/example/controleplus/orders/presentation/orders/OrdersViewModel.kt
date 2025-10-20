package com.example.controleplus.orders.presentation.orders

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controleplus.core.util.DateFormatter
import com.example.controleplus.core.util.DateFormatter.atEndOfDay
import com.example.controleplus.core.util.DateFormatter.atStartOfDay
import com.example.controleplus.orders.domain.model.Orders
import com.example.controleplus.orders.domain.use_case.OrdersUseCases
import com.example.controleplus.orders.domain.util.OrderType
import com.example.controleplus.orders.domain.util.OrdersOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val ordersUseCases: OrdersUseCases
) : ViewModel() {

    private val _state = mutableStateOf(OrdersState())
    val state: State<OrdersState> = _state

    private var currentDate: LocalDateTime? = null
    private var currentStartDate: LocalDateTime? = null
    private var currentEndDate: LocalDateTime? = null

    private var recentlyDeletedOrder: Orders? = null

    private var getOrdersJob: Job? = null

    private fun getOrders(ordersOrder: OrdersOrder) {
        getOrdersJob?.cancel()
        getOrdersJob = ordersUseCases.getOrders(ordersOrder)
            .onEach { orders ->
                _state.value = state.value.copy(
                    orders = orders,
                    ordersOrder = ordersOrder
                )
            }
            .launchIn(viewModelScope)
    }

    private fun getIncomeOrders(ordersOrder: OrdersOrder) {
        getOrdersJob?.cancel()
        getOrdersJob = ordersUseCases.getIncomeOrders(ordersOrder)
            .onEach { orders ->
                _state.value = state.value.copy(
                    orders = orders,
                    ordersOrder = ordersOrder
                )
            }
            .launchIn(viewModelScope)
    }

    private fun getExpenseOrders(ordersOrder: OrdersOrder) {
        getOrdersJob?.cancel()
        getOrdersJob = ordersUseCases.getExpenseOrders(ordersOrder)
            .onEach { orders ->
                _state.value = state.value.copy(
                    orders = orders,
                    ordersOrder = ordersOrder
                )
            }
            .launchIn(viewModelScope)
    }

    private fun getOrdersBetweenDates(
        startDate: LocalDateTime,
        endDate: LocalDateTime,
        ordersOrder: OrdersOrder
    ) {
        getOrdersJob?.cancel()
        getOrdersJob =
            ordersUseCases.getOrdersBetweenDates(startDate, endDate, ordersOrder)
                .onEach { orders ->
                    _state.value = state.value.copy(
                        orders = orders,
                        ordersOrder = ordersOrder
                    )
                }
                .launchIn(viewModelScope)
    }

    private fun getIncomeOrdersBetweenDates(
        startDate: LocalDateTime,
        endDate: LocalDateTime,
        ordersOrder: OrdersOrder
    ) {
        getOrdersJob?.cancel()
        getOrdersJob =
            ordersUseCases.getIncomeOrdersBetweenDates(startDate, endDate, ordersOrder)
                .onEach { orders ->
                    _state.value = state.value.copy(
                        orders = orders,
                        ordersOrder = ordersOrder
                    )
                }
                .launchIn(viewModelScope)
    }

    private fun getExpenseOrdersBetweenDates(
        startDate: LocalDateTime,
        endDate: LocalDateTime,
        ordersOrder: OrdersOrder
    ) {
        getOrdersJob?.cancel()
        getOrdersJob =
            ordersUseCases.getExpenseOrdersBetweenDates(startDate, endDate, ordersOrder)
                .onEach { orders ->
                    _state.value = state.value.copy(
                        orders = orders,
                        ordersOrder = ordersOrder
                    )
                }
                .launchIn(viewModelScope)
    }

    private fun reloadOrdersWithCurrentFilters(ordersOrder: OrdersOrder = state.value.ordersOrder) {
        val order = ordersOrder

        when {
            currentStartDate != null && currentEndDate != null -> {
                val start = currentStartDate!!.atStartOfDay()
                val end = currentEndDate!!.atEndOfDay()

                when (state.value.selectedType) {
                    "income" -> getIncomeOrdersBetweenDates(start, end, order)
                    "expense" -> getExpenseOrdersBetweenDates(start, end, order)
                    else -> getOrdersBetweenDates(start, end, order)
                }
            }

            currentDate != null -> {
                val start = currentDate!!.atStartOfDay()
                val end = currentDate!!.atEndOfDay()

                when (state.value.selectedType) {
                    "income" -> getIncomeOrdersBetweenDates(start, end, order)
                    "expense" -> getExpenseOrdersBetweenDates(start, end, order)
                    else -> getOrdersBetweenDates(start, end, order)
                }
            }

            else -> {
                when (state.value.selectedType) {
                    "income" -> getIncomeOrders(order)
                    "expense" -> getExpenseOrders(order)
                    else -> getOrders(order)
                }
            }
        }
    }


    init {
        getOrders(OrdersOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: OrdersEvent) {
        when (event) {

            is OrdersEvent.Order -> {
                val order = event.ordersOrder

                val selectedField = when (order) {
                    is OrdersOrder.Amount -> "amount"
                    is OrdersOrder.Category -> "category"
                    is OrdersOrder.Type -> "type"
                    is OrdersOrder.Date -> "date"
                }

                _state.value = state.value.copy(selectedOrder = selectedField)

                reloadOrdersWithCurrentFilters(order)
            }

            is OrdersEvent.LoadAll -> {
                _state.value = state.value.copy(selectedType = "all")
                reloadOrdersWithCurrentFilters(OrdersOrder.Date(OrderType.Descending))
            }

            is OrdersEvent.LoadIncome -> {
                _state.value = state.value.copy(selectedType = "income")
                reloadOrdersWithCurrentFilters(OrdersOrder.Date(OrderType.Descending))
            }

            is OrdersEvent.LoadExpense -> {
                _state.value = state.value.copy(selectedType = "expense")
                reloadOrdersWithCurrentFilters(OrdersOrder.Date(OrderType.Descending))
            }

            is OrdersEvent.ClearDateFilters -> {
                currentDate = null
                currentStartDate = null
                currentEndDate = null
                _state.value = state.value.copy(
                    formattedCurrentDate = "",
                    formattedStartDate = "",
                    formattedEndDate = ""
                )
                reloadOrdersWithCurrentFilters(state.value.ordersOrder)
            }

            is OrdersEvent.LoadByDate -> {
                currentDate = event.date
                _state.value = state.value.copy(
                    formattedCurrentDate = DateFormatter.formatDate(event.date),
                    formattedStartDate = "",
                    formattedEndDate = ""
                )

                val start = event.date.atStartOfDay()
                val end = event.date.atEndOfDay()

                when (state.value.selectedType) {
                    "income" -> getIncomeOrdersBetweenDates(
                        start,
                        end,
                        OrdersOrder.Date(OrderType.Descending)
                    )

                    "expense" -> getExpenseOrdersBetweenDates(
                        start,
                        end,
                        OrdersOrder.Date(OrderType.Descending)
                    )

                    else -> getOrdersBetweenDates(
                        start,
                        end,
                        OrdersOrder.Date(OrderType.Descending)
                    )
                }
            }

            is OrdersEvent.LoadBetweenDates -> {
                currentDate = null
                currentStartDate = event.startDate
                currentEndDate = event.endDate

                _state.value = state.value.copy(
                    formattedStartDate = DateFormatter.formatDate(event.startDate),
                    formattedEndDate = DateFormatter.formatDate(event.endDate),
                    formattedCurrentDate = ""
                )

                val start = event.startDate.atStartOfDay()
                val end = event.endDate.atEndOfDay()

                when (state.value.selectedType) {
                    "income" -> getIncomeOrdersBetweenDates(
                        start,
                        end,
                        OrdersOrder.Date(OrderType.Descending)
                    )

                    "expense" -> getExpenseOrdersBetweenDates(
                        start,
                        end,
                        OrdersOrder.Date(OrderType.Descending)
                    )

                    else -> getOrdersBetweenDates(
                        start,
                        end,
                        OrdersOrder.Date(OrderType.Descending)
                    )
                }
            }

            is OrdersEvent.ToggleOrderType -> {
                val newOrderType = if (state.value.ordersOrder.orderType is OrderType.Ascending) {
                    OrderType.Descending
                } else {
                    OrderType.Ascending
                }

                val newOrder = when (state.value.ordersOrder) {
                    is OrdersOrder.Date -> OrdersOrder.Date(newOrderType)
                    is OrdersOrder.Amount -> OrdersOrder.Amount(newOrderType)
                    is OrdersOrder.Category -> OrdersOrder.Category(newOrderType)
                    is OrdersOrder.Type -> OrdersOrder.Type(newOrderType)
                }

                _state.value = state.value.copy(ordersOrder = newOrder)

                reloadOrdersWithCurrentFilters(newOrder)
            }

            is OrdersEvent.DeleteOrders -> {
                viewModelScope.launch {
                    ordersUseCases.deleteOrders(event.orders)
                    recentlyDeletedOrder = event.orders
                }
            }

            is OrdersEvent.RestoreOrders -> {
                viewModelScope.launch {
                    ordersUseCases.addOrders(recentlyDeletedOrder ?: return@launch)
                    recentlyDeletedOrder = null
                }
            }

            is OrdersEvent.ToggleTypeSection -> {
                _state.value = state.value.copy(
                    isTypeSectionVisible = !state.value.isTypeSectionVisible
                )
            }

            is OrdersEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }

            is OrdersEvent.ToggleDateSection -> {
                _state.value = state.value.copy(
                    isDateSectionVisible = !state.value.isDateSectionVisible
                )
            }
        }
    }
}