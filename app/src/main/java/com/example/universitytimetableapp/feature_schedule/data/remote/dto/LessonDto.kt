package com.example.universitytimetableapp.feature_schedule.data.remote.dto

import com.example.universitytimetableapp.feature_schedule.domain.model.Lesson

data class LessonDto(
    val dayOfWeek: String,
    val endDate: String,
    val frequency: Int,
    val groups: List<GroupDto>,
    val id: String,
    val lessonTime: LessonTimeDto,
    val lessonType: LessonTypeDto,
    val startDate: String,
    val studyRoom: StudyRoomDto,
    val subject: SubjectDto,
    val teacher: TeacherDto
) {
    fun toLesson(): Lesson {
        return Lesson(
            dayOfWeek = dayOfWeek,
            endDate = endDate,
            frequency = frequency,
            groups = groups.map { it.toGroup() },
            id = id,
            lessonTime = lessonTime.toLessonTime(),
            lessonType = lessonType.toLessonType(),
            startDate = startDate,
            studyRoom = studyRoom.toStudyRoom(),
            subject = subject.toSubject(),
            teacher = teacher.toTeacher()
        )
    }
}