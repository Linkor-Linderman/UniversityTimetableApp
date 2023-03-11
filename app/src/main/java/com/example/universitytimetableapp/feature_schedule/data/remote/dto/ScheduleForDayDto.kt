package com.example.universitytimetableapp.feature_schedule.data.remote.dto

import com.example.universitytimetableapp.feature_schedule.domain.model.ScheduleForDay

data class ScheduleForDayDto(
    val date: List<Int>,
    val dayOfWeek: String,
    val lessons: List<LessonDto>
) {
    fun toScheduleForDay(): ScheduleForDay {
        return ScheduleForDay(
            date = date,
            dayOfWeek = dayOfWeek,
            lessons = lessons.map { it.toLesson() }
        )
    }
}