package com.example.praktikum2_124140024.ui

import androidx.lifecycle.ViewModel
import com.example.praktikum2_124140024.data.PenyimpananLaporan
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

    fun kirim() {
        val state = _uiState.value
        if (!state.bisaKirim) return

        PenyimpananLaporan.tambah(
            judul = state.judul.trim(),
            lokasi = state.lokasi.trim(),
            deskripsi = state.deskripsi.trim()
        )
        _uiState.value = FormLaporanUiState(tersimpan = true)
    }

    fun resetTersimpan() {
        _uiState.update { it.copy(tersimpan = false) }
    }
}