package com.example.universitytimetableapp.feature_schedule.domain.model

data class ScheduleForDay(
    val date: String,
    val lessons: List<Lesson>
)