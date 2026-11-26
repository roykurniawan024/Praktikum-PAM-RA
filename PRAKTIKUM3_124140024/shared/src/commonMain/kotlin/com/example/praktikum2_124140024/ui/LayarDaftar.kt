package com.example.praktikum2_124140024.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LayarDaftar() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Laporan 1: Lampu jalan mati di Gerbang Utama")
        Text("Laporan 2: Jalan berlubang dekat Gedung C")
        Text("Laporan 3: Keran air bocor di Masjid At-Tanwir")
    }
}