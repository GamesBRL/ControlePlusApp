package com.example.controleplus.feature_orders.presentation.orders

import androidx.lifecycle.ViewModel
import com.example.controleplus.feature_orders.domain.use_case.OrdersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrdersViewModel @Inject constructor(
    private val ordersUseCases: OrdersUseCases
) : ViewModel() {

}