package com.example.praktikum2_124140024

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform