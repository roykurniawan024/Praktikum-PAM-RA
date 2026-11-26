package com.example.praktikum2_124140024.praktikum4

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import praktikum2_124140024.shared.generated.resources.Res
import praktikum2_124140024.shared.generated.resources.foto_saya

private val LightColors = lightColorScheme(
    primary = Color(0xFF0A66C2),
    onPrimary = Color.White,
    background = Color(0xFFF3F2EF),
    onBackground = Color(0xFF1B1B1B),
    surface = Color.White,
    onSurface = Color(0xFF333333),
    surfaceVariant = Color(0xFFE8F0FE),
    onSurfaceVariant = Color(0xFF546E7A),
    outlineVariant = Color(0xFFEEEEEE)
)

private val DarkColors = darkColorScheme(
    primary = Color(0xFF70B5F9),
    onPrimary = Color(0xFF002F5C),
    background = Color(0xFF121212),
    onBackground = Color(0xFFEAEAEA),
    surface = Color(0xFF1E1E1E),
    onSurface = Color(0xFFE0E0E0),
    surfaceVariant = Color(0xFF263238),
    onSurfaceVariant = Color(0xFFB0BEC5),
    outlineVariant = Color(0xFF2E2E2E)
)

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = viewModel { ProfileViewModel() }
) {
    val uiState by viewModel.uiState.collectAsState()

    MaterialTheme(
        colorScheme = if (uiState.isDarkMode) DarkColors else LightColors
    ) {
        ProfileContent(
            uiState = uiState,
            onToggleDarkMode = viewModel::toggleDarkMode,
            onToggleExtraDetails = viewModel::toggleExtraDetails,
            onStartEditing = viewModel::startEditing,
            onCancelEditing = viewModel::cancelEditing,
            onEditNameChange = viewModel::onEditNameChange,
            onEditBioChange = viewModel::onEditBioChange,
            onSaveProfile = viewModel::saveProfile
        )
    }
}

@Composable
fun ProfileContent(
    uiState: ProfileUiState,
    onToggleDarkMode: () -> Unit,
    onToggleExtraDetails: () -> Unit,
    onStartEditing: () -> Unit,
    onCancelEditing: () -> Unit,
    onEditNameChange: (String) -> Unit,
    onEditBioChange: (String) -> Unit,
    onSaveProfile: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Dark Mode",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.width(8.dp))
            Switch(
                checked = uiState.isDarkMode,
                onCheckedChange = { onToggleDarkMode() }
            )
        }

        ProfileHeader(
            name = uiState.name,
            bio = uiState.bio,
            avatarRes = Res.drawable.foto_saya
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (uiState.isEditing) {
            EditProfileForm(
                name = uiState.editName,
                bio = uiState.editBio,
                canSave = uiState.canSave,
                onNameChange = onEditNameChange,
                onBioChange = onEditBioChange,
                onSave = onSaveProfile,
                onCancel = onCancelEditing
            )
        } else {
            OutlinedButton(
                onClick = onStartEditing,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Profil",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Edit Profil")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        ProfileCard(
            email = uiState.email,
            phone = uiState.phone,
            location = uiState.location
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onToggleExtraDetails,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = "Keahlian Saya",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedVisibility(visible = uiState.showExtraDetails) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Text(
                    text = "• UI/UX Design\n• Pemrograman: C++ & Java\n• Database: PostgreSQL",
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    lineHeight = 26.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun EditProfileForm(
    name: String,
    bio: String,
    canSave: Boolean,
    onNameChange: (String) -> Unit,
    onBioChange: (String) -> Unit,
    onSave: () -> Unit,
    onCancel: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Edit Profil",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                label = { Text("Nama") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = bio,
                onValueChange = onBioChange,
                label = { Text("Bio") },
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = onCancel,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Batal")
                }
                Button(
                    onClick = onSave,
                    enabled = canSave,
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Simpan")
                }
            }
        }
    }
}

@Composable
fun ProfileHeader(name: String, bio: String, avatarRes: DrawableResource) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
        ) {
            Image(
                painter = painterResource(avatarRes),
                contentDescription = "Foto Profil $name",
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .border(4.dp, MaterialTheme.colorScheme.primary, CircleShape),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = name,
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = bio,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 28.dp),
            lineHeight = 20.sp
        )
    }
}

@Composable
fun InfoItem(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp, horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "Ikon $text",
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.width(18.dp))
        Text(
            text = text,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ProfileCard(email: String, phone: String, location: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(modifier = Modifier.padding(6.dp)) {
            InfoItem(icon = Icons.Default.Email, text = email)
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)
            InfoItem(icon = Icons.Default.Phone, text = phone)
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)
            InfoItem(icon = Icons.Default.LocationOn, text = location)
        }
    }
}