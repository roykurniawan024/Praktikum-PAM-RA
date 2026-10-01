package com.example.praktikum2_124140024.praktikum3

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.DrawableResource
import praktikum2_124140024.shared.generated.resources.Res
import praktikum2_124140024.shared.generated.resources.foto_saya

@Composable
fun ProfileHeader(name: String, bio: String, avatarRes: DrawableResource) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(top = 32.dp, bottom = 18.dp)
        ) {
            Image(
                painter = painterResource(avatarRes),
                contentDescription = "Foto Profil $name",
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .border(4.dp, Color(0xFF0A66C2), CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = name,
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF1B1B1B)
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = bio,
            fontSize = 14.sp,
            color = Color(0xFF546E7A),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 28.dp),
            lineHeight = 20.sp
        )
    }
}

@Composable
fun InfoItem(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 14.dp, horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Ikon $text",
            tint = Color(0xFF0A66C2)
        )
        Spacer(modifier = Modifier.width(18.dp))
        Text(
            text = text,
            fontSize = 15.sp,
            color = Color(0xFF333333),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ProfileCard(email: String, phone: String, location: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(6.dp)) {
            InfoItem(icon = Icons.Default.Email, text = email)
            HorizontalDivider(thickness = 1.dp, color = Color(0xFFEEEEEE))
            InfoItem(icon = Icons.Default.Phone, text = phone)
            HorizontalDivider(thickness = 1.dp, color = Color(0xFFEEEEEE))
            InfoItem(icon = Icons.Default.LocationOn, text = location)
        }
    }
}

@Composable
fun MyProfileApp() {
    var showExtraDetails by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF3F2EF)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileHeader(
            name = "Roy Kurniawan",
            bio = "Mahasiswa Teknik Informatika ITERA",
            avatarRes = Res.drawable.foto_saya
        )
        Spacer(modifier = Modifier.height(28.dp))
        ProfileCard(
            email = "roy.124140024@student.itera.ac.id",
            phone = "+62 858-4123-4015",
            location = "Lampung, Indonesia"
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { showExtraDetails = !showExtraDetails },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp).height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0A66C2))
        ) {
            Text(
                text = "Keahlian Saya",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        AnimatedVisibility(visible = showExtraDetails) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F0FE)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = "• UI/UX Design\n• Pemrograman: C++ & Java\n• Database: PostgreSQL",
                    modifier = Modifier.padding(20.dp).fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    lineHeight = 26.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}