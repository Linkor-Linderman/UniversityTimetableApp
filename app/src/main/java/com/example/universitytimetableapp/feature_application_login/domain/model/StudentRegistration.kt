package com.example.universitytimetableapp.feature_application_login.domain.model

data class StudentRegistration(
    val surname: String,
    val name: String,
    val patronymic: String,
    val groupId: String,
    val email: String,
    val password: String
)
