package com.example.universitytimetableapp.feature_application_login.data.dto

import com.example.universitytimetableapp.feature_application_login.domain.model.SelectionItem

data class GroupDto(
    val number: String,
    val id: String
) {
    fun toSelectionItem(): SelectionItem {
        return SelectionItem(
            id = id,
            name = number
        )
    }
}
