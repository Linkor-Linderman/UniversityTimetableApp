package com.example.universitytimetableapp.feature_schedule.data.remote.dto

import com.example.universitytimetableapp.feature_schedule.domain.model.StudyRoom

data class StudyRoomDto(
    val buildingNumber: Int,
    val floor: Int?,
    val id: String,
    val name: String?,
    val number: String
) {
    fun toStudyRoom(): StudyRoom {
        return StudyRoom(
            buildingNumber = buildingNumber,
            floor = floor,
            name = name,
            number = number
        )
    }
}