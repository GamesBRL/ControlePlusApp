package com.example.controleplus.feature_orders.presentation.add_orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.controleplus.R
import com.example.controleplus.core.components.DefaultRadioButton
import com.example.controleplus.core.util.Screen
import com.example.controleplus.feature_orders.presentation.add_orders.components.TransparentHintField
import com.example.controleplus.ui.theme.Black
import com.example.controleplus.ui.theme.DarkGray
import com.example.controleplus.ui.theme.White2
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AddOrdersScreen(
    navController: NavController,
    viewModel: AddOrdersViewModel = hiltViewModel()
) {

    val amountState = viewModel.orderAmount.value
    val categoryState = viewModel.orderCategory.value

    var selectedType by remember { mutableStateOf("income") }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {

                is AddOrdersViewModel.UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = event.massage
                    )
                }

                is AddOrdersViewModel.UiEvent.SaveOrder -> {
                    navController.navigate(Screen.OrdersScreen.route)
                }
            }
        }
    }

    Scaffold { contentPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Black)
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Text(
                text = stringResource(R.string.title_addordersscreen),
                color = White2,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(200.dp))
            TransparentHintField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                textStyle = TextStyle(color = White2),
                text = amountState.text,
                hint = stringResource(R.string.hint_amount_addordersscreen),
                onValueChange = {
                    viewModel.onEvent(AddOrdersEvent.EnteredAmount(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddOrdersEvent.ChangeAmountFocus(it))
                },
                isHintVisible = amountState.isHintVisible,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .height(50.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            TransparentHintField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = TextStyle(color = White2),
                text = categoryState.text,
                hint = stringResource(R.string.hint_category_addordersscreen),
                onValueChange = {
                    viewModel.onEvent(AddOrdersEvent.EnteredCategory(it))
                },
                onFocusChange = {
                    viewModel.onEvent(AddOrdersEvent.ChangeCategoryFocus(it))
                },
                isHintVisible = categoryState.isHintVisible,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .height(50.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .clip(RoundedCornerShape(15.dp))
                    .background(DarkGray)
                    .padding(15.dp)
            ) {
                Text(
                    text = stringResource(R.string.title_typesection_addordersscreen),
                    color = White2,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(5.dp))
                DefaultRadioButton(
                    modifier = Modifier.align(Alignment.Start)
                        .padding(start = 65.dp),
                    text = stringResource(R.string.incomebutton_typesection_addordersscreen),
                    selected = selectedType == "income",
                    onSelect = { selectedType = "income" }
                )
                DefaultRadioButton(
                    modifier = Modifier.align(Alignment.Start)
                        .padding(start = 65.dp),
                    text = stringResource(R.string.expensebutton_typesection_addordersscreen),
                    selected = selectedType == "expense",
                    onSelect = { selectedType = "expense" }
                )
            }
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Button(
                    onClick = {
                        navController.navigateUp()
                    },
                    colors = ButtonColors(
                        containerColor = DarkGray,
                        contentColor = White2,
                        disabledContainerColor = DarkGray,
                        disabledContentColor = White2,
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Cancel,
                        contentDescription = "Back Arrow"
                    )
                }
                Button(
                    onClick = {
                        viewModel.onEvent(AddOrdersEvent.ChangeType(selectedType))
                        viewModel.onEvent(AddOrdersEvent.SaveOrder)
                    },
                    colors = ButtonColors(
                        containerColor = DarkGray,
                        contentColor = White2,
                        disabledContainerColor = DarkGray,
                        disabledContentColor = White2,
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Save Button"
                    )
                }
            }
        }
    }
}