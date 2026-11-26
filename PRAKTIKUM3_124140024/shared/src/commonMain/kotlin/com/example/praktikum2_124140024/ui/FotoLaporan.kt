package com.example.praktikum2_124140024.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LabelStatus(
    status: String,
    modifier: Modifier = Modifier
) {
    val warnaLatar = when (status) {
        "Baru" -> Color(0xFF1976D2)
        "Diproses" -> Color(0xFFF57C00)
        "Selesai" -> Color(0xFF388E3C)
        else -> Color(0xFFD32F2F)
    }

    Text(
        text = status,
        color = Color.White,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(warnaLatar)
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}

@Composable
fun FotoLaporan(
    status: String,
    keteranganFoto: String = "Foto Laporan"
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE0E0E0)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = keteranganFoto, color = Color.DarkGray)
        }

        LabelStatus(
            status = status,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(12.dp)
        )
    }
}