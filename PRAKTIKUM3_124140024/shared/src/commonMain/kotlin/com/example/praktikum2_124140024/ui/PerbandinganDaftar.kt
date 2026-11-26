package com.example.praktikum2_124140024.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

val daftar200Laporan = List(200) { indeks ->
    val nomor = indeks + 1
    val daftarStatus = listOf("Baru", "Diproses", "Selesai")
    Laporan(
        id = nomor,
        judul = "Laporan Kerusakan #$nomor",
        lokasi = "Area Kampus Blok ${(indeks % 10) + 1}",
        waktu = "$nomor menit lalu",
        status = daftarStatus[indeks % daftarStatus.size],
        timestamp = 1732600000L - (nomor * 60L)
    )
}

@Composable
fun DaftarDenganColumn(daftarLaporan: List<Laporan> = daftar200Laporan) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        daftarLaporan.forEach { laporan ->
            KartuLaporan(
                judul = laporan.judul,
                lokasi = laporan.lokasi,
                waktu = laporan.waktu,
                status = laporan.status,
                ringkas = true
            )
        }
    }
}

@Composable
fun DaftarDenganLazyColumn(daftarLaporan: List<Laporan> = daftar200Laporan) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(daftarLaporan, key = { it.id }) { laporan ->
            KartuLaporan(
                judul = laporan.judul,
                lokasi = laporan.lokasi,
                waktu = laporan.waktu,
                status = laporan.status,
                ringkas = true
            )
        }
    }
}