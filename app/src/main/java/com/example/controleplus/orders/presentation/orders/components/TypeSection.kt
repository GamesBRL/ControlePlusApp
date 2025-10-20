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
fun TypeSection(
    modifier: Modifier = Modifier,
    selectedType: String = "",
    onSelectType: (String) -> Unit
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
                text = stringResource(R.string.all_word),
                selected = selectedType == "all",
                onSelect = { onSelectType("all") },
                modifier = Modifier.align(Alignment.Start)
            )
            DefaultRadioButton(
                text = stringResource(R.string.income_singular),
                selected = selectedType == "income",
                onSelect = { onSelectType("income") },
                modifier = Modifier.align(Alignment.Start)
            )
            DefaultRadioButton(
                text = stringResource(R.string.expense_singular),
                selected = selectedType == "expense",
                onSelect = { onSelectType("expense") },
                modifier = Modifier.align(Alignment.Start)
            )
        }
    }
}

@Preview
@Composable
fun TypeSectionPreview() {

}