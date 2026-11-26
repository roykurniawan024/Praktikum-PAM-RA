package com.example.praktikum2_124140024.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun UjiUrutanModifier() {
    Box(
        modifier = Modifier.border(1.dp, Color.Gray)
    ) {
        Text(
            text = "Contoh Modifier",
            modifier = Modifier
                .padding(16.dp)
                .background(Color(0xFFFFF176))
                .padding(8.dp)
        )
    }
}