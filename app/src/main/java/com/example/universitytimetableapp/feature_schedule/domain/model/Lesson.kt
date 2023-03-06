package com.example.universitytimetableapp.feature_schedule.domain.model

data class Lesson(
    val dayOfWeek: String,
    val endDate: String,
    val frequency: Int,
    val groups: List<Group>,
    val id: String,
    val lessonTime: LessonTime,
    val lessonType: LessonType,
    val startDate: String,
    val studyRoom: StudyRoom,
    val subject: Subject,
    val teacher: Teacher
)