package com.example.praktikum2_124140024.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FormLaporan() {
    var judul = ""

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = judul,
            onValueChange = {
                println(it)
                judul = it
            },
            label = { Text("Judul Laporan") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}