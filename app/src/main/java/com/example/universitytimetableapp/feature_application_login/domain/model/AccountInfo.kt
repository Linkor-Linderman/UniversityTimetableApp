package com.example.universitytimetableapp.feature_application_login.domain.model

import com.example.universitytimetableapp.feature_application_login.data.dto.GroupDto

data class AccountInfo(
    val full_name: String,
    val role: String,
    val group: GroupDto?,
    val teacherId: String
)
