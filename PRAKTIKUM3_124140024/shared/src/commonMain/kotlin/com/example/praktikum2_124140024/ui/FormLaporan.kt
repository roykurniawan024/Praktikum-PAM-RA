package com.example.praktikum2_124140024.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FormLaporanScreen(
    viewModel: FormLaporanViewModel = viewModel { FormLaporanViewModel() }
) {
    val uiState by viewModel.uiState.collectAsState()

    FormLaporan(
        uiState = uiState,
        onJudulBerubah = viewModel::ubahJudul,
        onLokasiBerubah = viewModel::ubahLokasi,
        onDeskripsiBerubah = viewModel::ubahDeskripsi,
        onKosongkan = viewModel::kosongkan
    )
}

@Composable
fun FormLaporan(
    uiState: FormLaporanUiState,
    onJudulBerubah: (String) -> Unit,
    onLokasiBerubah: (String) -> Unit,
    onDeskripsiBerubah: (String) -> Unit,
    onKosongkan: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        KolomTeks(
            label = "Judul Laporan",
            nilai = uiState.judul,
            onNilaiBerubah = onJudulBerubah
        )
        KolomTeks(
            label = "Lokasi",
            nilai = uiState.lokasi,
            onNilaiBerubah = onLokasiBerubah
        )
        KolomTeks(
            label = "Deskripsi",
            nilai = uiState.deskripsi,
            onNilaiBerubah = onDeskripsiBerubah
        )
        Button(
            onClick = onKosongkan,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kosongkan")
        }
    }
}

@Composable
fun KolomTeks(
    label: String,
    nilai: String,
    onNilaiBerubah: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = nilai,
        onValueChange = onNilaiBerubah,
        label = { Text(label) },
        modifier = modifier.fillMaxWidth()
    )
}