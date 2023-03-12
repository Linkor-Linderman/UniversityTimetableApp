package com.example.universitytimetableapp.feature_schedule.data.remote.dto

import com.example.universitytimetableapp.feature_schedule.domain.model.Group

data class GroupDto(
    val id: String,
    val number: String
) {
    fun toGroup(): Group {
        return Group(
            id = id,
            number = number
        )
    }
}