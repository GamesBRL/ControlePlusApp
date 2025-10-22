package com.example.controleplus.orders.presentation.add_orders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.controleplus.core.components.DefaultRadioButton
import com.example.controleplus.orders.presentation.add_orders.components.TransparentHintField
import com.example.controleplus.ui.theme.Black
import com.example.controleplus.ui.theme.DarkGray
import com.example.controleplus.ui.theme.White2

@Preview
@Composable
fun AddOrdersScreenPreview() {
    Scaffold { contentPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Black)
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Text(
                "New Order",
                color = White2,
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                TransparentHintField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    text = "",
                    hint = "Value: $ 0.00",
                    onValueChange = {},
                    onFocusChange = {},
                    modifier = Modifier
                        .padding(bottom = 445.dp)
                        .fillMaxWidth(0.75f)
                        .height(50.dp)
                )
                TransparentHintField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    text = "",
                    hint = "Categoy: Salary, Rent",
                    onValueChange = {},
                    onFocusChange = {},
                    modifier = Modifier
                        .padding(bottom = 280.dp)
                        .fillMaxWidth(0.75f)
                        .height(50.dp)
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .clip(RoundedCornerShape(15.dp))
                        .background(DarkGray)
                        .padding(15.dp)
                ) {
                    Text("Type", color = White2, style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(5.dp))
                    DefaultRadioButton(
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 65.dp),
                        text = "Income",
                        selected = true,
                        onSelect = {}
                    )
                    DefaultRadioButton(
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(start = 65.dp),
                        text = "Expense",
                        selected = false,
                        onSelect = {}
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 250.dp)
                ) {
                    Button(
                        onClick = {},
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
                        onClick = {},
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
}