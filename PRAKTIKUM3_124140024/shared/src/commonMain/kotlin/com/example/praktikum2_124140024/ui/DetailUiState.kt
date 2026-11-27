package com.example.praktikum2_124140024.ui

import com.example.praktikum2_124140024.data.Laporan

sealed interface DetailUiState {
    data object Memuat : DetailUiState
    data class Ada(val laporan: Laporan) : DetailUiState
    data object TidakDitemukan : DetailUiState
}