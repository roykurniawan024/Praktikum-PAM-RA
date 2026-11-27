package com.example.praktikum2_124140024

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.praktikum2_124140024.data.PenyimpananLaporan
import com.example.praktikum2_124140024.praktikum4.ProfileScreen
import com.example.praktikum2_124140024.ui.DaftarLaporanScreen
import com.example.praktikum2_124140024.ui.FormLaporanScreen

@Composable
@Preview
fun App() {
    val navController = rememberNavController()

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .safeContentPadding()
        ) {
            NavHost(
                navController = navController,
                startDestination = RuteDaftar
            ) {
                composable<RuteDaftar> {
                    DaftarLaporanScreen(
                        onTambahLaporan = { navController.navigate(RuteForm) },
                        onPilihLaporan = { id -> navController.navigate(RuteDetail(id)) }
                    )
                }

                composable<RuteDetail> { entry ->
                    val rute = entry.toRoute<RuteDetail>()
                    val daftar by PenyimpananLaporan.daftar.collectAsState()
                    val laporan = daftar.find { it.id == rute.id }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Detail Laporan #${rute.id}",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        if (laporan != null) {
                            Text(laporan.judul, style = MaterialTheme.typography.titleMedium)
                            Text("Lokasi: ${laporan.lokasi}")
                            Text("Deskripsi: ${laporan.deskripsi}")
                        } else {
                            Text("Laporan tidak ditemukan.")
                        }
                        Button(onClick = { navController.popBackStack() }) {
                            Text("Kembali")
                        }
                    }
                }

                composable<RuteForm> {
                    FormLaporanScreen(
                        selesai = { navController.popBackStack() }
                    )
                }

                composable<RuteStatistik> {
                    val daftar by PenyimpananLaporan.daftar.collectAsState()
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text("Statistik Laporan", style = MaterialTheme.typography.headlineSmall)
                        Text("Total laporan: ${daftar.size}")
                    }
                }

                composable<RuteProfil> {
                    ProfileScreen()
                }
            }
        }
    }
}