package com.example.universitytimetableapp.feature_application_login.data.dto

import com.example.universitytimetableapp.feature_application_login.domain.model.GroupsTeachersItem

data class GroupDto(
    val number: String,
    val id: String
) {
    fun toGroupsTeachersItem(): GroupsTeachersItem {
        return GroupsTeachersItem(
            id = id,
            name = number
        )
    }
}
