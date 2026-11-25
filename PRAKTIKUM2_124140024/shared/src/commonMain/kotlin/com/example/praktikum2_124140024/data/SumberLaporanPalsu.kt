package com.example.praktikum2_124140024.data

import com.example.praktikum2_124140024.model.Laporan
import com.example.praktikum2_124140024.model.StatusLaporan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
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
}