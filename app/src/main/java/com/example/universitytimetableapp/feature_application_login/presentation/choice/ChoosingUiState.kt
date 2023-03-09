package com.example.universitytimetableapp.feature_application_login.presentation.choice

data class ChoosingUiState(
    val isLoading: Boolean = false,
    val isShowMessage: Boolean = false,
    val message: String = "",
    val isClassroomShow: Boolean = false,
    val isRoleChosen: Boolean = false,
    val isShowDialog: Boolean = false,
    val mayNavigate: Boolean = false,
    val destinationString: String = ""
)
