package com.example.universitytimetableapp.feature_profile.data.dto

import com.example.universitytimetableapp.feature_profile.domain.model.PasswordChange

data class PasswordModifyDto(
    val oldPassword: String,
    val newPassword: String
) {
    companion object {
        fun fromPasswordChange(passwordChange: PasswordChange): PasswordModifyDto {
            return PasswordModifyDto(
                oldPassword = passwordChange.oldPassword,
                newPassword = passwordChange.newPassword
            )
        }
    }
}
