package com.example.praktikum2_124140024.ui

fun ulangi(kali: Int, aksi: (Int) -> Unit) {
    for (indeks in 0 until kali) {
        aksi(indeks)
    }
}

fun contohUlangi() {
    ulangi(3) { indeks ->
        println("Perulangan ke-${indeks + 1}")
    }
}