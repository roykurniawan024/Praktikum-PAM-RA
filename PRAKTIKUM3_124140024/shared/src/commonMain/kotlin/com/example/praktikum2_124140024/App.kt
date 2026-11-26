package com.example.praktikum2_124140024

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.praktikum2_124140024.praktikum4.ProfileScreen

@Composable
@Preview
fun App() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeContentPadding()
    ) {
        ProfileScreen()
    }
}