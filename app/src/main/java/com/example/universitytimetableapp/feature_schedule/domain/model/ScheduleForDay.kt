package com.example.universitytimetableapp.feature_schedule.domain.model

data class ScheduleForDay(
    val date: String,
    val dayOfWeek: String,
    val lessons: List<Lesson>
)