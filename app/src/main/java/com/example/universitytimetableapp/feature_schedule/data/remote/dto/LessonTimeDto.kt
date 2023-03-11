package com.example.universitytimetableapp.feature_schedule.data.remote.dto

import com.example.universitytimetableapp.feature_schedule.domain.model.LessonTime
import com.example.universitytimetableapp.feature_schedule.domain.model.LocalTimeModel

data class LessonTimeDto(
    val endTime: List<Int>,
    val lessonNumber: Int,
    val startTime: List<Int>
) {
    fun toLessonTime(): LessonTime {
        return LessonTime(
            endTime = LocalTimeModel(
                endTime[0],
                endTime[1],
                null,
                null
            ),
            lessonNumber = lessonNumber,
            startTime = LocalTimeModel(
                startTime[0],
                startTime[1],
                null,
                null
            )
        )
    }
}