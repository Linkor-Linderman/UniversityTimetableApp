package com.example.universitytimetableapp.feature_profile.presentation.profile

import com.example.universitytimetableapp.feature_profile.domain.model.UserAccount

data class ProfileUiState(
    val isLoading: Boolean = false,
    val isShowMessage: Boolean = false,
    val message: String = "",
    val profile: UserAccount? = null,
    val isGuest: Boolean = true,
    val isStudent: Boolean = true,
    val isShowDialog: Boolean = false,
    val mayNavigate: Boolean = false,
    val destinationString: String = "",
    val isExit: Boolean = false
)