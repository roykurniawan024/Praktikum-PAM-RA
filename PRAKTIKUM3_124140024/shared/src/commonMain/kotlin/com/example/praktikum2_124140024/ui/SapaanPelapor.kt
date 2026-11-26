package com.example.praktikum2_124140024.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SapaanPelapor(nama: String, jumlahLaporan: Int = 0) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Halo, $nama",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A73E8)
        )
        Text(
            text = "Kamu memiliki $jumlahLaporan laporan aktif",
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}