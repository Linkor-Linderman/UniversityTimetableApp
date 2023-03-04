package com.example.universitytimetableapp.feature_application_login.data.dto

import com.example.universitytimetableapp.feature_application_login.domain.model.GroupsTeachersItem

data class TeacherDto(
    val firstName: String,
    val lastName: String,
    val patronymicName: String,
    val id: String
) {
    fun toGroupsTeachersItem(): GroupsTeachersItem {
        return GroupsTeachersItem(
            id = id,
            name = "$lastName $firstName $patronymicName"
        )
    }
}
