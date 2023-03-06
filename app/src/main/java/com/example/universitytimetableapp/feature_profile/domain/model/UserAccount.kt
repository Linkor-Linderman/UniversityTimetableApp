package com.example.universitytimetableapp.feature_profile.domain.model

data class UserAccount(
    val id: String,
    val name: String,
    val surname: String,
    val patronymic: String?,
    val role: String,
    val groupNumber: String?,
    val email: String
)
