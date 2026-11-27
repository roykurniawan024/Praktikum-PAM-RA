package com.example.praktikum2_124140024.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Laporan(
    val id: Int,
    val judul: String,
    val lokasi: String,
    val deskripsi: String
)

object PenyimpananLaporan {
    private val dataAwal = listOf(
        Laporan(1, "Lampu jalan mati", "Gerbang Utama", "Lampu padam sejak kemarin malam"),
        Laporan(2, "Jalan berlubang", "Dekat Gedung C", "Lubang cukup dalam di tikungan"),
        Laporan(3, "Keran air bocor", "Masjid At-Tanwir", "Keran wudhu sisi kiri terus menetes"),
        Laporan(4, "AC tidak dingin", "Gedung Kuliah Umum 1", "Ruang 204 terasa panas saat siang"),
        Laporan(5, "Proyektor buram", "Lab IoT", "Tampilan layar berkedip dan redup"),
        Laporan(6, "Parkiran penuh", "Area Gedung E", "Motor parkir melewati garis jalur"),
        Laporan(7, "WiFi putus-putus", "Perpustakaan GKU 2 ITERA", "Koneksi sering terputus tiba-tiba")
    )

    private var idBerikutnya = dataAwal.size + 1
    private val _daftar = MutableStateFlow(dataAwal)
    val daftar: StateFlow<List<Laporan>> = _daftar.asStateFlow()

    fun tambah(judul: String, lokasi: String, deskripsi: String) {
        val laporanBaru = Laporan(
            id = idBerikutnya++,
            judul = judul,
            lokasi = lokasi,
            deskripsi = deskripsi
        )
        _daftar.update { it + laporanBaru }
    }
}