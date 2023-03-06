package com.example.universitytimetableapp.feature_schedule.domain.model

data class LessonTime(
    val endTime: EndTime,
    val lessonNumber: Int,
    val startTime: StartTime
) {
    fun getTimePeriod(): String {
        return "${startTime.hour}:${startTime.minute} - ${endTime.hour}:${endTime.minute}"
    }
}