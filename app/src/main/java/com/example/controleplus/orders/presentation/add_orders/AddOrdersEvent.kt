package com.example.controleplus.orders.presentation.add_orders

import androidx.compose.ui.focus.FocusState

sealed class AddOrdersEvent {

    data class EnteredAmount(val value: String): AddOrdersEvent()
    data class ChangeAmountFocus(val focusState: FocusState): AddOrdersEvent()
    data class EnteredCategory(val value: String): AddOrdersEvent()
    data class ChangeCategoryFocus(val focusState: FocusState): AddOrdersEvent()
    data class ChangeType(val value: String): AddOrdersEvent()
    object SaveOrder: AddOrdersEvent()
}