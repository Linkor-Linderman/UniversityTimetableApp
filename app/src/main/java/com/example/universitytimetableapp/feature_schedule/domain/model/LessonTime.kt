package com.example.universitytimetableapp.feature_schedule.domain.model

data class LessonTime(
    val endTime: LocalTimeModel,
    val lessonNumber: Int,
    val startTime: LocalTimeModel
) {
    fun getTimePeriod(): String {
        return "${startTime.getHourAndMinute()} - ${endTime.getHourAndMinute()}"
    }
}