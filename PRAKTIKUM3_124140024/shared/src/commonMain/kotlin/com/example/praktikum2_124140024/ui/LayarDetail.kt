package com.example.praktikum2_124140024.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LayarDetail(
    idLaporan: Int,
    kembali: () -> Unit,
    viewModel: DetailViewModel = viewModel(key = "detail_$idLaporan") {
        DetailViewModel(idLaporan)
    }
) {
    val uiState by viewModel.uiState.collectAsState()

    when (val state = uiState) {
        DetailUiState.Memuat -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is DetailUiState.Ada -> {
            val laporan = state.laporan
            val statusBerikutnya = when (laporan.status) {
                "Menunggu" -> "Diproses"
                "Diproses" -> "Selesai"
                else -> "Menunggu"
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Detail Laporan #${laporan.id}",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(laporan.judul, style = MaterialTheme.typography.titleMedium)
                Text("Lokasi: ${laporan.lokasi}")
                Text("Deskripsi: ${laporan.deskripsi}")
                Text("Status: ${laporan.status}")
                Button(onClick = { viewModel.ubahStatus(statusBerikutnya) }) {
                    Text("Ubah Status ke $statusBerikutnya")
                }
                Button(onClick = kembali) {
                    Text("Kembali")
                }
            }
        }

        DetailUiState.TidakDitemukan -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Laporan #$idLaporan tidak ditemukan.",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
                Button(onClick = kembali) {
                    Text("Kembali")
                }
            }
        }
    }
}