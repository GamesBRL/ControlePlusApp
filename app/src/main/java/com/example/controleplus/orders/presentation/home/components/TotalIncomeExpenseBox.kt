package com.example.controleplus.orders.presentation.home.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.controleplus.R
import com.example.controleplus.core.util.CurrencyUtils
import com.example.controleplus.ui.theme.DarkGray
import com.example.controleplus.ui.theme.LightGreen
import com.example.controleplus.ui.theme.Red

@Composable
fun TotalIncomeExpenseBox(
    type: String,
    amount: String,
    modifier: Modifier = Modifier,
) {

    val color = when (type) {
        "income" -> LightGreen
        "expense" -> Red
        "balance" -> Color.White
        else -> Color.White
    }

    val title = when (type) {
        "income" -> stringResource(R.string.income_plural)
        "expense" -> stringResource(R.string.expense_plural)
        "balance" -> stringResource(R.string.balance_singular)
        else -> ""
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(DarkGray)
    ) {

        val padding = if (CurrencyUtils.parseCurrencyToDouble(amount) > 1000000) 10.dp else 5.dp

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .padding(padding)
        ) {
            Text(
                text = title,
                color = color,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = amount,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
fun IncomeExpenseBoxPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(Color.Yellow)
                .fillMaxSize()
        ) {
            TotalIncomeExpenseBox(type = "income", amount = "1000")
            Spacer(modifier = Modifier.height(25.dp))
            TotalIncomeExpenseBox(type = "expense", amount = "1050.0")
            Spacer(modifier = Modifier.height(25.dp))
            TotalIncomeExpenseBox(type = "balance", amount = "1250.0")
        }
    }
}