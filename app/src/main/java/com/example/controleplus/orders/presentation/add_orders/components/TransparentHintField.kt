package com.example.controleplus.orders.presentation.add_orders.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.controleplus.ui.theme.DarkGray
import com.example.controleplus.ui.theme.LightGray2

@Composable
fun TransparentHintField(
    keyboardOptions: KeyboardOptions,
    text: String,
    hint: String,
    modifier: Modifier = Modifier,
    isHintVisible: Boolean = true,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle = TextStyle(),
    onFocusChange: (FocusState) -> Unit,
) {

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(DarkGray)
            .padding(horizontal = 18.dp)
    ) {
        BasicTextField(
            keyboardOptions = keyboardOptions,
            value = text,
            onValueChange = onValueChange,
            textStyle = textStyle,
            singleLine = true,
            modifier = Modifier
                .onFocusChanged { onFocusChange(it) }
                .fillMaxWidth()
                .align(Alignment.CenterStart)
        )
        if (isHintVisible) {
            Text(
                text = hint,
                style = textStyle,
                color = LightGray2,
                modifier = Modifier.align(Alignment.CenterStart)
            )
        }
    }
}

@Preview
@Composable
fun TransparentHintFieldPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        TransparentHintField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            text = "",
            hint = "R$ 0,0",
            isHintVisible = true,
            onValueChange = {},
            onFocusChange = {},
            textStyle = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.fillMaxWidth(0.75f)
        )
        Spacer(modifier = Modifier.height(10.dp))
        TransparentHintField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            text = "",
            hint = "Ex: Salário, Aluguel",
            isHintVisible = true,
            onValueChange = {},
            onFocusChange = {},
            textStyle = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .height(100.dp)
        )
    }
}