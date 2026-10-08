package com.example.floreria.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SettingsViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun updateStoreName(newName: String) {
        _uiState.update { currentState ->
            currentState.copy(storeName = newName)
        }
    }

    fun toggleDarkMode(enabled: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(isDarkMode = enabled)
        }
    }

    fun updateLanguage(languageCode: String) {
        _uiState.update { currentState ->
            currentState.copy(selectedLanguage = languageCode)
        }
    }

    fun toggleAutoSync(enabled: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(autoSyncEnabled = enabled)
        }
    }

    fun updateClosingTime(time: String) {
        _uiState.update { currentState ->
            currentState.copy(closingTime = time)
        }
    }
}