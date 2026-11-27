package com.example.praktikum2_124140024.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praktikum2_124140024.data.PenyimpananLaporan

@Composable
fun DaftarLaporanScreen(
    onTambahLaporan: () -> Unit,
    onPilihLaporan: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val daftarLaporan by PenyimpananLaporan.daftar.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = onTambahLaporan,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tambah Laporan")
        }

        if (daftarLaporan.isEmpty()) {
            Text("Belum ada laporan.")
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(daftarLaporan, key = { it.id }) { laporan ->
                    Card(
                        onClick = { onPilihLaporan(laporan.id) },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = "#${laporan.id} - ${laporan.judul}",
                                style = MaterialTheme.typography.titleMedium
                            )
                            if (laporan.lokasi.isNotBlank()) {
                                Text(
                                    text = laporan.lokasi,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            if (laporan.deskripsi.isNotBlank()) {
                                Text(
                                    text = laporan.deskripsi,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}