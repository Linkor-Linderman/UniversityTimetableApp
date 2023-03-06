package com.example.universitytimetableapp.feature_schedule.domain.model

sealed class ScheduleItem {
    data class SubjectItem(
        val lesson: Lesson
    ) : ScheduleItem()

    data class WindowItem(
        val windowNumber: String,
        val startTime: StartTime,
        val endTime: EndTime
    ) : ScheduleItem()

    data class BreakItem(
        val startTime: EndTime,
        val endTime: StartTime
    ) : ScheduleItem()
}
