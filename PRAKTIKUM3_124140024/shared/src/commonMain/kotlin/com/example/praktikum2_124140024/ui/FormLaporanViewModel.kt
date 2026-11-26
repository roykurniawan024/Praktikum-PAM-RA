package com.example.praktikum2_124140024.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FormLaporanViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FormLaporanUiState())
    val uiState: StateFlow<FormLaporanUiState> = _uiState.asStateFlow()

    fun ubahJudul(nilai: String) {
        _uiState.update { it.copy(judul = nilai) }
    }

    fun ubahLokasi(nilai: String) {
        _uiState.update { it.copy(lokasi = nilai) }
    }

    fun ubahDeskripsi(nilai: String) {
        _uiState.update { it.copy(deskripsi = nilai) }
    }

    fun kosongkan() {
        _uiState.value = FormLaporanUiState()
    }
}