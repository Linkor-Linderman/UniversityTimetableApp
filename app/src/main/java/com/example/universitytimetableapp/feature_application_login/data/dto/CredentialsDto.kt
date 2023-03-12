package com.example.universitytimetableapp.feature_application_login.data.dto

import com.example.universitytimetableapp.feature_application_login.domain.model.LoginCredentials

data class CredentialsDto(
    val email: String,
    val password: String
) {
    companion object {
        fun fromLoginCredentials(loginCredentials: LoginCredentials): CredentialsDto {
            return CredentialsDto(
                email = loginCredentials.email,
                password = loginCredentials.password
            )
        }
    }
}
