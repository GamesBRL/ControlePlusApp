package com.example.controleplus.orders.presentation.orders.components

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.controleplus.R

@Composable
fun DateSelector(
    onDateRangeSelected: (Pair<Long, Long>) -> Unit,
    onDismiss: () -> Unit
) {
    var step by remember { mutableIntStateOf(1) }
    var startDateMillis by remember { mutableStateOf<Long?>(null) }

    val datePickerState = rememberDatePickerState()
    val context = LocalContext.current

    DatePickerDialog(
        onDismissRequest = {
            step = 1
            startDateMillis = null
            onDismiss()
        },
        confirmButton = {
            TextButton(onClick = {
                val selected = datePickerState.selectedDateMillis

                if (selected == null) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.null_date),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@TextButton
                }

                if (step == 1) {
                    startDateMillis = selected
                    step = 2
                } else {
                    val start = startDateMillis
                    if (start == null) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.null_date),
                            Toast.LENGTH_SHORT
                        ).show()
                        return@TextButton
                    }

                    if (selected < start) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.invalid_end_date),
                            Toast.LENGTH_SHORT
                        ).show()
                        return@TextButton
                    }
                    onDateRangeSelected(Pair(start, selected))
                    step = 1
                    startDateMillis = null
                    onDismiss()
                }
            }) {
                Text(if (step == 1) stringResource(R.string.next_button) else "OK")
            }
        },
        dismissButton = {
            TextButton(onClick = {
                step = 1
                startDateMillis = null
                onDismiss()
            }) {
                Text(stringResource(R.string.cancel_button))
            }
        }
    ) {
        DatePicker(
            state = datePickerState,
            title = {
                Text(
                    text = if (step == 1)
                        stringResource(R.string.select_initial_date)
                    else
                        stringResource(R.string.select_final_date),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
            },
            showModeToggle = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
        )
    }
}

@Preview
@Composable
fun DateSelectorPreview() {
    DateSelector(
        onDateRangeSelected = { TODO() },
        onDismiss = { TODO() }
    )
}