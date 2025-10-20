package com.example.controleplus.orders.presentation.orders.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.controleplus.R
import com.example.controleplus.core.components.DefaultRadioButton
import com.example.controleplus.ui.theme.DarkGray

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    selectedOrder: String = "date",
    onSelectOrder: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(DarkGray)
            .padding(top = 10.dp, bottom = 10.dp)
            .fillMaxWidth()
    ) {
        Column {
            DefaultRadioButton(
                text = stringResource(R.string.date_word),
                selected = selectedOrder == "date",
                onSelect = { onSelectOrder("date") },
                modifier = Modifier.align(Alignment.Start)
            )
            DefaultRadioButton(
                text = stringResource(R.string.amount_word),
                selected = selectedOrder == "amount",
                onSelect = { onSelectOrder("amount") },
                modifier = Modifier.align(Alignment.Start)
            )
            DefaultRadioButton(
                text = stringResource(R.string.category_word),
                selected = selectedOrder == "category",
                onSelect = { onSelectOrder("category") },
                modifier = Modifier.align(Alignment.Start)
            )
            DefaultRadioButton(
                text = stringResource(R.string.type_word),
                selected = selectedOrder == "type",
                onSelect = { onSelectOrder("type") },
                modifier = Modifier.align(Alignment.Start)
            )
        }
    }
}


@Preview
@Composable
fun OrderSectionPreview() {

}