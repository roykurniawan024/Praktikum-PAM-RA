package com.example.praktikum2_124140024.model

sealed class StatusLaporan {
    data object Baru : StatusLaporan()
    data object Diproses : StatusLaporan()
    data object Selesai : StatusLaporan()
}

data class Laporan(
    val id: Int,
    val judul: String,
    val lokasi: String,
    val status: StatusLaporan = StatusLaporan.Baru,
    val fotoUrl: String? = null
)

fun StatusLaporan.label(): String = when (this) {
    StatusLaporan.Baru -> "Baru"
    StatusLaporan.Diproses -> "Diproses"
    StatusLaporan.Selesai -> "Selesai"
}

fun Laporan.ringkas(): String {
    return "#$id - $judul [${status.label()}]"
}

fun Laporan.adaFoto(): Boolean {
    return !fotoUrl.isNullOrBlank()
}