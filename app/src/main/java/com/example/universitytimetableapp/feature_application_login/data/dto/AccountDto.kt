package com.example.universitytimetableapp.feature_application_login.data.dto

import com.example.universitytimetableapp.feature_application_login.domain.model.AccountInfo

data class AccountDto(
    val id: String,
    val firstName: String,
    val lastName: String,
    val patronymicName: String?,
    val role: String,
    val group: GroupDto?,
    val teacherId: String,
    val email: String
) {
    fun toAccountInfo(): AccountInfo {
        return AccountInfo(
            full_name = "$lastName $firstName ${patronymicName?: ""}".trimEnd(),
            role = role,
            group = group,
            teacherId = teacherId,
        )
    }
}
