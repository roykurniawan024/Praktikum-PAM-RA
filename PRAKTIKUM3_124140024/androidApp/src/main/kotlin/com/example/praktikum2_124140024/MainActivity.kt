package com.example.praktikum2_124140024

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.praktikum2_124140024.praktikum3.MyProfileApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            MyProfileApp()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    MyProfileApp()
}