package com.example.universitytimetableapp.feature_schedule.data.remote.dto

import com.example.universitytimetableapp.feature_schedule.domain.model.LessonTime
import com.example.universitytimetableapp.feature_schedule.domain.model.LocalTimeModel

data class LessonTimeDto(
    val endTime: LocalTimeDto,
    val lessonNumber: Int,
    val startTime: LocalTimeDto
) {
    fun toLessonTime(): LessonTime {
        return LessonTime(
            endTime = endTime.toLocalTimeModel(),
            lessonNumber = lessonNumber,
            startTime = startTime.toLocalTimeModel()
        )
    }
}