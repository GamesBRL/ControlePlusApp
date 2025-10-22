package com.example.controleplus.orders.presentation.orders

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controleplus.core.util.DateFormatter
import com.example.controleplus.orders.domain.model.Orders
import com.example.controleplus.orders.domain.use_case.OrdersUseCases
import com.example.controleplus.orders.domain.util.OrderType
import com.example.controleplus.orders.domain.util.OrdersOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val ordersUseCases: OrdersUseCases
) : ViewModel() {

    private val _state = mutableStateOf(OrdersState())
    val state: State<OrdersState> = _state

    //Variáveis de auxilio para os eventos
    private var currentDate: Long? = null
    private var currentStartDate: Long? = null
    private var currentEndDate: Long? = null

    private var recentlyDeletedOrder: Orders? = null

    private var getOrdersJob: Job? = null

    //As funções abaixo são getters que vão retornar as ORDENS de acordo com os eventos
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
        startDate: Long,
        endDate: Long,
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
        startDate: Long,
        endDate: Long,
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
        startDate: Long,
        endDate: Long,
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

    //Esta função garante que, caso haja algum filtro de Data(intervalo ou único, respeite outros filtros de Ordenação
    //sem limpar o filtro de Data(intervalo ou único) existente
    private fun reloadOrdersWithCurrentFilters(ordersOrder: OrdersOrder = state.value.ordersOrder) {
        val order = ordersOrder

        when {
            currentStartDate != null && currentEndDate != null -> {
                val start = currentStartDate!!
                val end = currentEndDate!!

                when (state.value.selectedType) {
                    "income" -> getIncomeOrdersBetweenDates(start, end, order)
                    "expense" -> getExpenseOrdersBetweenDates(start, end, order)
                    else -> getOrdersBetweenDates(start, end, order)
                }
            }

            currentDate != null -> {
                val start = currentDate!!
                val end = currentDate!!

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

    //Inicialmente a lista de ORDENS exibidas será por padrão a data em ordem decrescente,
    //ou seja, após o usuário concluir a adição da ORDEM, ela será exibida no topo da lista
    init {
        getOrders(OrdersOrder.Date(OrderType.Descending))
    }

    //Eventos que serão ativados na camada de View
    fun onEvent(event: OrdersEvent) {
        when (event) {

            //Evento que retorna as ORDENS de acordo com os filtros de Ordenação
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

            //Evento que carrega as ORDENS de todos os TIPOS
            is OrdersEvent.LoadAll -> {
                _state.value = state.value.copy(selectedType = "all")
                reloadOrdersWithCurrentFilters(OrdersOrder.Date(OrderType.Descending))
            }

            //Evento que carrega as ORDENS do TIPO entrada
            is OrdersEvent.LoadIncome -> {
                _state.value = state.value.copy(selectedType = "income")
                reloadOrdersWithCurrentFilters(OrdersOrder.Date(OrderType.Descending))
            }

            //Evento que carrega as ORDENS do TIPO despesa
            is OrdersEvent.LoadExpense -> {
                _state.value = state.value.copy(selectedType = "expense")
                reloadOrdersWithCurrentFilters(OrdersOrder.Date(OrderType.Descending))
            }

            //Evento que limpa os filtros de Data(intervalo ou único) existentes
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

            //Evento que filtra as ORDENS por data, no caso, uma unica data
            //respeitando o filtro por TIPO
            is OrdersEvent.LoadByDate -> {
                currentDate = event.date
                _state.value = state.value.copy(
                    formattedCurrentDate = DateFormatter.formatDate(event.date),
                    formattedStartDate = "",
                    formattedEndDate = ""
                )

                val start = event.date
                val end = event.date

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

            //Evento que filtra as ORDENS por intervalo de datas, respeitando o filtro por TIPO
            is OrdersEvent.LoadBetweenDates -> {
                currentDate = null
                currentStartDate = event.startDate
                currentEndDate = event.endDate

                _state.value = state.value.copy(
                    formattedStartDate = DateFormatter.formatDate(event.startDate),
                    formattedEndDate = DateFormatter.formatDate(event.endDate),
                    formattedCurrentDate = ""
                )

                val start = event.startDate
                val end = event.endDate

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

            //Evento que alterna os tipos de Ordenação, Crescente e Decrescente
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

            //Evento que deleta a ORDEM selecionada
            is OrdersEvent.DeleteOrders -> {
                viewModelScope.launch {
                    ordersUseCases.deleteOrders(event.orders)
                    recentlyDeletedOrder = event.orders
                }
            }

            //Evento que restaura a ORDEM mais recente deletada
            //apenas a última ORDEM deletada pode ser restaurada
            is OrdersEvent.RestoreOrders -> {
                viewModelScope.launch {
                    ordersUseCases.addOrders(recentlyDeletedOrder ?: return@launch)
                    recentlyDeletedOrder = null
                }
            }

            //Evento que alterna a visualização do campo de seleção de TIPO
            is OrdersEvent.ToggleTypeSection -> {
                _state.value = state.value.copy(
                    isTypeSectionVisible = !state.value.isTypeSectionVisible
                )
            }

            //Evento que alterna a visualização do campo de seleção de Ordenação
            is OrdersEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }

            //Evento que permite visualizar o DatePicker
            is OrdersEvent.ToggleDateSection -> {
                _state.value = state.value.copy(
                    isDateSectionVisible = !state.value.isDateSectionVisible
                )
            }
        }
    }
}