package com.example.universitytimetableapp.feature_schedule.domain.model

data class ScheduleForDay(
    val date: List<Int>,
    val dayOfWeek: String,
    val lessons: List<Lesson>
)