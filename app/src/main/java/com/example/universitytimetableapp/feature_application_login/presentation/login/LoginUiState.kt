package com.example.universitytimetableapp.feature_application_login.presentation.login

data class LoginUiState(
    val isShowMessage: Boolean = false,
    val message: String = "",
    val mayNavigate: Boolean = false,
    val destinationString: String = ""
)
