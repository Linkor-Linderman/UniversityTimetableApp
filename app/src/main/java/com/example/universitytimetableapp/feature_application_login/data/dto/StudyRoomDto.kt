package com.example.universitytimetableapp.feature_application_login.data.dto

import com.example.universitytimetableapp.feature_application_login.domain.model.SelectionItem

data class StudyRoomDto(
    val buildingNumber: Int,
    val floor: Int?,
    val name: String,
    val number: String,
    val id: String
) {
    fun toSelectionItem(): SelectionItem {
        val floorString = if (floor == null)
            ""
        else
            ", $floor этаж"
        return SelectionItem(
            id = id,
            name = "$number ($buildingNumber корпус$floorString) $name"
        )
    }
}
