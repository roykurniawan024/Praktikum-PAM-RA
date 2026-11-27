package com.example.praktikum2_124140024.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktikum2_124140024.data.PenyimpananLaporan
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailViewModel(
    private val idLaporan: Int
) : ViewModel() {

    val uiState: StateFlow<DetailUiState> = PenyimpananLaporan.daftar
        .map { daftar ->
            val laporan = daftar.find { it.id == idLaporan }
            if (laporan != null) {
                DetailUiState.Ada(laporan)
            } else {
                DetailUiState.TidakDitemukan
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DetailUiState.Memuat
        )

    fun ubahStatus(statusBaru: String) {
        PenyimpananLaporan.ubahStatus(idLaporan, statusBaru)
    }
}