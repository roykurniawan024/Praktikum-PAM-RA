package com.example.praktikum2_124140024.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun KartuLaporan(
    judul: String,
    lokasi: String,
    waktu: String,
    status: String,
    ringkas: Boolean = false,
    onKlik: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onKlik() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            if (!ringkas) {
                FotoLaporan(status = status)
                Spacer(modifier = Modifier.height(8.dp))
            } else {
                LabelStatus(status = status)
                Spacer(modifier = Modifier.height(6.dp))
            }

            Text(
                text = judul,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            BarisLokasi(lokasi = lokasi, waktu = waktu)
        }
    }
}

@Composable
fun ContohKartuLaporan() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        KartuLaporan(
            judul = "Lampu jalan mati di Gerbang Utama",
            lokasi = "Gerbang Utama ITERA",
            waktu = "10 menit lalu",
            status = "Baru"
        )

        KartuLaporan(
            judul = "Jalan berlubang dekat Gedung C",
            lokasi = "Dekat Gedung C",
            waktu = "1 jam lalu",
            status = "Diproses",
            ringkas = true
        )

        KartuLaporan(
            judul = "Keran air bocor di Masjid",
            lokasi = "Masjid At-Tanwir",
            waktu = "2 jam lalu",
            status = "Selesai",
            ringkas = false
        ) {
            println("Laporan diklik: Keran air bocor di Masjid")
        }
    }
}