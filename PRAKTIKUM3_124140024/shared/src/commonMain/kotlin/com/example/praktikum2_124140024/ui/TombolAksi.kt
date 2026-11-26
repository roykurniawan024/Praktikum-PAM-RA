package com.example.praktikum2_124140024.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TombolAksi(
    onKirim: () -> Unit = {},
    onSimpanDraf: () -> Unit = {},
    onBatal: () -> Unit = {}
) {
    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(onClick = onKirim) {
            Text("Kirim")
        }
        OutlinedButton(onClick = onSimpanDraf) {
            Text("Draf")
        }
        TextButton(onClick = onBatal) {
            Text("Batal")
        }
    }
}