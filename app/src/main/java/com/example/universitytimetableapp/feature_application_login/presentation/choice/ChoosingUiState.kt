package com.example.universitytimetableapp.feature_application_login.presentation.choice

data class ChoosingUiState(
    val isLoading: Boolean = false,
    val isShowMessage: Boolean = false,
    val message: String = "",
    val isRoleChosen: Boolean = false,
    val itemList: List<String> = listOf("972101", "972102", "972103", "972105", "972110", "972201", "972202", "972203", "972205", "972210"),
    val isShowDialog: Boolean = false,
    val mayNavigate: Boolean = false,
    val destinationString: String = ""
)
