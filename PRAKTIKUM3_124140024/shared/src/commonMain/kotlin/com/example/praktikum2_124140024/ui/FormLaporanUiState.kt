package com.example.praktikum2_124140024.ui

data class FormLaporanUiState(
    val judul: String = "",
    val lokasi: String = "",
    val deskripsi: String = ""
) {
    val bisaKirim: Boolean
        get() = judul.trim().length >= 3

    val pesanGalat: String?
        get() = if (judul.isNotEmpty() && !bisaKirim) {
            "Judul minimal 3 huruf"
        } else {
            null
        }
}