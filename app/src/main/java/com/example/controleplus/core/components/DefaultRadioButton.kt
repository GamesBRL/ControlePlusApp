package com.example.controleplus.core.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.controleplus.ui.theme.LightGray
import com.example.controleplus.ui.theme.White2

@Composable
fun DefaultRadioButton(
    text: String,
    selected: Boolean,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){
        RadioButton(
            selected = selected,
            onClick = onSelect,
            colors = RadioButtonDefaults.colors(
                selectedColor = White2,
                unselectedColor = LightGray
            )
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = text, color = Color.White, modifier = Modifier.padding(end = 15.dp))
    }
}

@Preview
@Composable
fun DefaultRadioButtonPreview() {
    Column {
        DefaultRadioButton(
            text = "TextoParaVisualizacao",
            selected = true,
            onSelect = {}
        )
        Spacer(modifier = Modifier.height(8.dp))
        DefaultRadioButton(
            text = "TextoParaVisualizacao",
            selected = false,
            onSelect = {}
        )
    }
}