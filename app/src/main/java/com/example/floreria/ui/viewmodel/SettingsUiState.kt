package com.example.floreria.ui.viewmodel

data class SettingsUiState(
    val storeName: String = "Florería Central",
    val isDarkMode: Boolean = false,
    val selectedLanguage: String = "ES", // "ES" o "EN"
    val closingTime: String = "20:00",
    val autoSyncEnabled: Boolean = true
)