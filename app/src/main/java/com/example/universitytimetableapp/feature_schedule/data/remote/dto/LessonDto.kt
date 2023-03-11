package com.example.universitytimetableapp.feature_schedule.data.remote.dto

import com.example.universitytimetableapp.feature_schedule.domain.model.Lesson

data class LessonDto(
    val groups: List<GroupDto>,
    val id: String,
    val lessonTime: LessonTimeDto,
    val lessonType: LessonTypeDto,
    val studyRoom: StudyRoomDto?,
    val subject: SubjectDto,
    val teacher: TeacherDto
) {
    fun toLesson(): Lesson {
        return Lesson(
            groups = groups.map { it.toGroup() },
            id = id,
            lessonTime = lessonTime.toLessonTime(),
            lessonType = lessonType.toLessonType(),
            studyRoom = studyRoom!!.toStudyRoom(),
            subject = subject.toSubject(),
            teacher = teacher.toTeacher()
        )
    }
}