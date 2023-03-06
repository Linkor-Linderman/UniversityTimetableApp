package com.example.universitytimetableapp.feature_profile.data.dto

import com.example.universitytimetableapp.feature_profile.domain.model.UserAccount

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
    fun toUserAccount(): UserAccount {
        return UserAccount(
            id = id,
            name = firstName,
            surname = lastName,
            patronymic = patronymicName,
            role = role,
            groupNumber = group?.number,
            email = email
        )
    }
}

