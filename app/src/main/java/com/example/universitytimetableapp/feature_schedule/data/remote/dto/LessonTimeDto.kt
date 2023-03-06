package com.example.universitytimetableapp.feature_schedule.data.remote.dto

import com.example.universitytimetableapp.feature_schedule.domain.model.LessonTime

data class LessonTimeDto(
    val endTime: EndTimeDto,
    val lessonNumber: Int,
    val startTime: StartTimeDto
) {
    fun toLessonTime(): LessonTime {
        return LessonTime(
            endTime = endTime.toEndTimeDto(),
            lessonNumber = lessonNumber,
            startTime = startTime.toStartTime()
        )
    }
}