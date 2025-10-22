package com.example.controleplus.orders.presentation.add_orders

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controleplus.core.util.CurrencyUtils
import com.example.controleplus.orders.domain.model.InvalidOrdersException
import com.example.controleplus.orders.domain.model.Orders
import com.example.controleplus.orders.domain.use_case.OrdersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val MAX_CATEGORY_LENGTH = 25

@HiltViewModel
class AddOrdersViewModel @Inject constructor(
    private val ordersUseCases: OrdersUseCases
) : ViewModel() {

    private val _orderAmount = mutableStateOf(OrdersTextFieldState())
    val orderAmount: State<OrdersTextFieldState> = _orderAmount

    private val _orderCategory = mutableStateOf(OrdersTextFieldState())
    val orderCategory: State<OrdersTextFieldState> = _orderCategory

    private val _orderType = mutableStateOf("income")
    val orderType: State<String> = _orderType

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    //Função de eventos
    fun onEvent(event: AddOrdersEvent) {
        when (event) {

            //Evento que altera o valor no campo de texto, permitindo apenas números,
            //e exibindo já no formato da moeda local
            is AddOrdersEvent.EnteredAmount -> {
                val digitsOnly = event.value.filter { it.isDigit() }
                val doubleValue = if (digitsOnly.isNotEmpty()) digitsOnly.toDouble() / 100 else 0.0

                if (doubleValue == 0.0) {
                    _orderAmount.value = orderAmount.value.copy(
                        text = ""
                    )
                } else {
                    _orderAmount.value = orderAmount.value.copy(
                        text = CurrencyUtils.formatCurrency(doubleValue)
                    )
                }
            }

            //Evento que retira a dica ao clickar no campo Amount
            is AddOrdersEvent.ChangeAmountFocus -> {
                _orderAmount.value = orderAmount.value.copy(
                    isHintVisible = !event.focusState.isFocused && orderAmount.value.text.isBlank()
                )
            }

            //Evento que altera o valor no campo de texto da categoria, com limite de caracteres
            is AddOrdersEvent.EnteredCategory -> {
                val newText = event.value.take(MAX_CATEGORY_LENGTH)
                    .split("\\s+".toRegex())
                    .joinToString(" ") { it.replaceFirstChar { char -> char.uppercaseChar() } }
                _orderCategory.value = orderCategory.value.copy(
                    text = newText
                )
            }

            //Evento que retira a dica ao clickar no campo Category
            is AddOrdersEvent.ChangeCategoryFocus -> {
                _orderCategory.value = orderCategory.value.copy(
                    isHintVisible = !event.focusState.isFocused && orderCategory.value.text.isBlank()
                )
            }

            //Evento que muda TIPO, entrada ou despesa
            is AddOrdersEvent.ChangeType -> {
                _orderType.value = event.value
            }

            //Evento que salva a ORDEM
            is AddOrdersEvent.SaveOrder -> {
                viewModelScope.launch {
                    try {
                        ordersUseCases.addOrders(
                            Orders(
                                amount = CurrencyUtils.parseCurrencyToDouble(orderAmount.value.text),
                                category = orderCategory.value.text,
                                type = orderType.value,
                                date = System.currentTimeMillis(),
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveOrder)
                    } catch (e: InvalidOrdersException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackBar(
                                massage = e.message ?: "Couldn't save order"
                            )
                        )
                    }
                }
            }
        }
    }

    sealed class UiEvent {
        object SaveOrder : UiEvent()
        data class ShowSnackBar(val massage: String) : UiEvent()
    }
}
