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
    private var idBerikutnya = 1
    private val _daftar = MutableStateFlow<List<Laporan>>(emptyList())
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