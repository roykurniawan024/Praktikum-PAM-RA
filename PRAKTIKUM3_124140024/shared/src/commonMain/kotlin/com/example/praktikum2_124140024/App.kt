package com.example.praktikum2_124140024

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.praktikum2_124140024.data.PenyimpananLaporan
import com.example.praktikum2_124140024.praktikum4.ProfileScreen
import com.example.praktikum2_124140024.ui.DaftarLaporanScreen
import com.example.praktikum2_124140024.ui.FormLaporanScreen

private data class TabBawah(
    val label: String,
    val rute: Any,
    val ikon: ImageVector
)

private val daftarTab = listOf(
    TabBawah("Daftar", RuteDaftar, Icons.AutoMirrored.Filled.List),
    TabBawah("Statistik", RuteStatistik, Icons.Default.BarChart),
    TabBawah("Profil", RuteProfil, Icons.Default.Person)
)

@Composable
@Preview
fun App() {
    val navController = rememberNavController()
    val entriSekarang by navController.currentBackStackEntryAsState()
    val tujuanSekarang = entriSekarang?.destination
    val tampilkanMenuBawah = daftarTab.any { tab ->
        tujuanSekarang?.hierarchy?.any { it.hasRoute(tab.rute::class) } == true
    }

    MaterialTheme {
        Scaffold(
            bottomBar = {
                if (tampilkanMenuBawah) {
                    NavigationBar {
                        daftarTab.forEach { tab ->
                            val terpilih = tujuanSekarang?.hierarchy?.any {
                                it.hasRoute(tab.rute::class)
                            } == true

                            NavigationBarItem(
                                selected = terpilih,
                                onClick = { navController.navigate(tab.rute) },
                                icon = { Icon(tab.ikon, contentDescription = tab.label) },
                                label = { Text(tab.label) }
                            )
                        }
                    }
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = RuteDaftar,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
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