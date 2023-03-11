package com.example.universitytimetableapp.feature_schedule.domain.model

sealed class ScheduleItem {
    data class SubjectItem(
        val lesson: Lesson
    ) : ScheduleItem()

    data class WindowItem(
        val windowNumber: String,
        val startTime: LocalTimeModel,
        val endTime: LocalTimeModel
    ) : ScheduleItem()

    data class BreakItem(
        val startTime: LocalTimeModel,
        val endTime: LocalTimeModel
    ) : ScheduleItem()
}
