package com.example.universitytimetableapp.feature_schedule.domain.model

data class Lesson(
    val groups: List<Group>,
    val id: String,
    val lessonTime: LessonTime,
    val lessonType: LessonType,
    val studyRoom: StudyRoom?,
    val subject: Subject,
    val teacher: Teacher
) {
    fun getGroupsString(): String {
        return buildString {
            groups.forEach {
                append(it.number + ", ")
            }
        }.dropLast(2)
    }
}