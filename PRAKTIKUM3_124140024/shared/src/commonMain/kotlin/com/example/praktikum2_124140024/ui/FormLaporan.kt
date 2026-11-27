package com.example.praktikum2_124140024.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.LaunchedEffect

@Composable
fun FormLaporanScreen(
    selesai: () -> Unit = {},
    viewModel: FormLaporanViewModel = viewModel { FormLaporanViewModel() }
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.tersimpan) {
        if (uiState.tersimpan) {
            selesai()
            viewModel.resetTersimpan()
        }
    }

    FormLaporan(
        uiState = uiState,
        onJudulBerubah = viewModel::ubahJudul,
        onLokasiBerubah = viewModel::ubahLokasi,
        onDeskripsiBerubah = viewModel::ubahDeskripsi,
        onKosongkan = viewModel::kosongkan,
        onKirim = viewModel::kirim
    )
}

@Composable
fun FormLaporan(
    uiState: FormLaporanUiState,
    onJudulBerubah: (String) -> Unit,
    onLokasiBerubah: (String) -> Unit,
    onDeskripsiBerubah: (String) -> Unit,
    onKosongkan: () -> Unit,
    onKirim: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        KolomTeks(
            label = "Judul Laporan",
            nilai = uiState.judul,
            onNilaiBerubah = onJudulBerubah,
            pesanGalat = uiState.pesanGalat
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = onKosongkan,
                modifier = Modifier.weight(1f)
            ) {
                Text("Kosongkan")
            }
            Button(
                onClick = onKirim,
                enabled = uiState.bisaKirim,
                modifier = Modifier.weight(1f)
            ) {
                Text("Kirim")
            }
        }
    }
}

@Composable
fun KolomTeks(
    label: String,
    nilai: String,
    onNilaiBerubah: (String) -> Unit,
    pesanGalat: String? = null,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = nilai,
        onValueChange = onNilaiBerubah,
        label = { Text(label) },
        isError = pesanGalat != null,
        supportingText = pesanGalat?.let { { Text(it) } },
        modifier = modifier.fillMaxWidth()
    )
}