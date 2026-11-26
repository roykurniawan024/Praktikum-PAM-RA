package com.example.praktikum2_124140024.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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

@Composable
fun FormLaporan() {
    var judul by rememberSaveable { mutableStateOf("") }
    var lokasi by rememberSaveable { mutableStateOf("") }
    var deskripsi by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        KolomTeks(
            label = "Judul Laporan",
            nilai = judul,
            onNilaiBerubah = { judul = it }
        )
        KolomTeks(
            label = "Lokasi",
            nilai = lokasi,
            onNilaiBerubah = { lokasi = it }
        )
        KolomTeks(
            label = "Deskripsi",
            nilai = deskripsi,
            onNilaiBerubah = { deskripsi = it }
        )
    }
}