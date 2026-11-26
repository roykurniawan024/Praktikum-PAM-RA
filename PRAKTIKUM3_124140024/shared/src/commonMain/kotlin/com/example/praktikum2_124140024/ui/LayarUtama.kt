package com.example.praktikum2_124140024.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Laporan(
    val id: Int,
    val judul: String,
    val lokasi: String,
    val waktu: String,
    val status: String,
    val timestamp: Long
)

val daftarLaporanContoh = listOf(
    Laporan(1, "Keran air bocor di Masjid", "Masjid At-Tanwir", "3 jam lalu", "Selesai", 1732590000L),
    Laporan(2, "Jalan berlubang dekat Gedung C", "Dekat Gedung C", "1 jam lalu", "Diproses", 1732597200L),
    Laporan(3, "Lampu jalan mati di Gerbang Utama", "Gerbang Utama ITERA", "10 menit lalu", "Baru", 1732600200L),
    Laporan(4, "AC kelas tidak dingin", "Gedung Kuliah Umum 2", "2 jam lalu", "Baru", 1732593600L),
    Laporan(5, "Pagar parkir rusak", "Parkiran Gedung E", "5 jam lalu", "Selesai", 1732582800L)
)

@Composable
fun LayarUtama(daftarLaporan: List<Laporan> = daftarLaporanContoh) {
    val laporanAktif = daftarLaporan
        .filter { it.status != "Selesai" }
        .sortedByDescending { it.timestamp }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(laporanAktif, key = { it.id }) { laporan ->
            KartuLaporan(
                judul = laporan.judul,
                lokasi = laporan.lokasi,
                waktu = laporan.waktu,
                status = laporan.status
            )
        }
    }
}