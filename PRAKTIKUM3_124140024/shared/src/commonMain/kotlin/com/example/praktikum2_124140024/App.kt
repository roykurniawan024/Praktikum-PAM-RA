package com.example.praktikum2_124140024

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.praktikum2_124140024.ui.DaftarLaporanScreen
import com.example.praktikum2_124140024.ui.FormLaporanScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        var tampilForm by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding()
        ) {
            if (tampilForm) {
                FormLaporanScreen(
                    onSelesai = { tampilForm = false }
                )
            } else {
                DaftarLaporanScreen(
                    onTambahLaporan = { tampilForm = true }
                )
            }
        }
    }
}