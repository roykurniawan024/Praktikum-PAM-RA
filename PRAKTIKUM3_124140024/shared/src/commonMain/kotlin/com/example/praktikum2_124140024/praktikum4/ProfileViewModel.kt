package com.example.praktikum2_124140024.praktikum4

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProfileViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun toggleDarkMode() {
        _uiState.update { it.copy(isDarkMode = !it.isDarkMode) }
    }

    fun toggleExtraDetails() {
        _uiState.update { it.copy(showExtraDetails = !it.showExtraDetails) }
    }

    fun startEditing() {
        _uiState.update {
            it.copy(
                isEditing = true,
                editName = it.name,
                editBio = it.bio
            )
        }
    }

    fun cancelEditing() {
        _uiState.update { it.copy(isEditing = false) }
    }

    fun onEditNameChange(value: String) {
        _uiState.update { it.copy(editName = value) }
    }

    fun onEditBioChange(value: String) {
        _uiState.update { it.copy(editBio = value) }
    }

    fun saveProfile() {
        _uiState.update { state ->
            if (!state.canSave) return@update state
            state.copy(
                name = state.editName.trim(),
                bio = state.editBio.trim(),
                isEditing = false
            )
        }
    }
}