package com.example.controleplus.orders.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.controleplus.core.util.CurrencyUtils
import com.example.controleplus.orders.domain.use_case.OrdersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val ordersUseCases: OrdersUseCases
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        loadHomeData()
    }

    //Função que agrupa todos os valores na variável State,
    //assim, na HomeScreen será possível acessar todos os valores
    //já formatados para a moeda local
    private fun loadHomeData() {
        viewModelScope.launch {
            combine(
                ordersUseCases.getTotalIncome(),
                ordersUseCases.getTotalExpense(),
            ) { income, expense ->

                val totalIncome = income ?: 0.0
                val totalExpense = expense ?: 0.0
                val totalBalance = totalIncome - totalExpense

                HomeState(
                    totalIncome = CurrencyUtils.formatCurrency(totalIncome),
                    totalExpense = CurrencyUtils.formatCurrency(totalExpense),
                    totalBalance = CurrencyUtils.formatCurrency(totalBalance)
                )
            }.collect { _state.value = it }
        }
    }
}