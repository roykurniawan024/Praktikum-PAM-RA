package com.example.praktikum2_124140024.data

import com.example.praktikum2_124140024.model.Laporan
import com.example.praktikum2_124140024.model.StatusLaporan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

object SumberLaporanPalsu {
    suspend fun muatDetail(id: Int): Laporan = withContext(Dispatchers.IO) {
        delay(500)
        Laporan(
            id = id,
            judul = "Lampu jalan mati di dekat gerbang",
            lokasi = "Gerbang Utama",
            status = StatusLaporan.Diproses,
            fotoUrl = "https://contoh.id/foto/$id.jpg"
        )
    }

    fun pantau(id: Int): Flow<Laporan> = flow {
        val awal = Laporan(
            id = id,
            judul = "Lampu jalan mati di dekat gerbang",
            lokasi = "Gerbang Utama",
            status = StatusLaporan.Baru,
            fotoUrl = "https://contoh.id/foto/$id.jpg"
        )
        emit(awal)
        delay(1000)
        emit(awal.copy(status = StatusLaporan.Diproses))
        delay(1000)
        emit(awal.copy(status = StatusLaporan.Selesai))
    }
}