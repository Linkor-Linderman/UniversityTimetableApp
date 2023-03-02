package com.example.universitytimetableapp.feature_profile.presentation.profile

data class ProfileUiState(
    val isLoading: Boolean = false,
    val isShowMessage: Boolean = false,
    val message: String = "",
    val isGuest: Boolean = true,
    val isStudent: Boolean = true,
    val isShowDialog: Boolean = false,
    val mayNavigate: Boolean = false,
    val destinationString: String = ""
)