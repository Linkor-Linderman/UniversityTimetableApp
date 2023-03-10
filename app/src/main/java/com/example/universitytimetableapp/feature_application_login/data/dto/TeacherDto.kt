package com.example.universitytimetableapp.feature_application_login.data.dto

import com.example.universitytimetableapp.feature_application_login.domain.model.SelectionItem

data class TeacherDto(
    val firstName: String,
    val lastName: String,
    val patronymicName: String?,
    val id: String
) {
    fun toSelectionItem(): SelectionItem {
        return SelectionItem(
            id = id,
            name = "$lastName $firstName ${patronymicName?: ""}".trimEnd()
        )
    }
}
