package com.example.universitytimetableapp.feature_schedule.data.remote.dto

import com.example.universitytimetableapp.feature_schedule.domain.model.LessonType

data class LessonTypeDto(
    val id: String,
    val name: String
) {
    fun toLessonType(): LessonType {
        return LessonType(
            id = id,
            name = name
        )
    }
}