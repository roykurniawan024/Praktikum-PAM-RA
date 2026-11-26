package com.example.praktikum2_124140024.praktikum4

data class ProfileUiState(
    val name: String = "Roy Kurniawan",
    val bio: String = "Mahasiswa Teknik Informatika ITERA",
    val email: String = "roy.124140024@student.itera.ac.id",
    val phone: String = "+62 858-4123-4567",
    val location: String = "Lampung, Indonesia",
    val isDarkMode: Boolean = false,
    val showExtraDetails: Boolean = false,
    val isEditing: Boolean = false,
    val editName: String = "",
    val editBio: String = ""
) {
    val canSave: Boolean
        get() = editName.trim().isNotEmpty() && editBio.trim().isNotEmpty()
}