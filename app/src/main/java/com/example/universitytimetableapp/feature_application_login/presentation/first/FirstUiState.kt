package com.example.universitytimetableapp.feature_application_login.presentation.first

data class FirstUiState(
    val isLoading: Boolean = false,
    val mayGoSchedule: Boolean = false,
    val destinationString: String = ""
)