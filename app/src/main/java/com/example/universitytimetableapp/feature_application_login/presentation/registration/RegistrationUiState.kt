package com.example.universitytimetableapp.feature_application_login.presentation.registration

data class RegistrationUiState(
    val isCorrectData: Boolean = false,
    val isShowMessage: Boolean = false,
    val message: String = "",
    val isShowDialog: Boolean = false,
    val mayNavigate: Boolean = false,
    val destinationString: String = ""
)
