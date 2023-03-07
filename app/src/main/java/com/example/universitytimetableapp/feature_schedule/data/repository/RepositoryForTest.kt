package com.example.universitytimetableapp.feature_schedule.data.repository

import com.example.universitytimetableapp.feature_schedule.data.remote.ScheduleApiService
import com.example.universitytimetableapp.feature_schedule.domain.model.*
import com.example.universitytimetableapp.feature_schedule.domain.repository.ScheduleFeatureRepository

class RepositoryForTest(
    val api: ScheduleApiService
) : ScheduleFeatureRepository {
    override suspend fun getScheduleForWeekByTeacherId(
        teacherId: String,
        startDate: String,
        endDate: String
    ): List<ScheduleForDay> {
        val listOfScheduleForDay = mutableListOf<ScheduleForDay>()
        val listOfLessons = mutableListOf<Lesson>()

        for (i in 0..5) {
            listOfLessons.add(
                Lesson(
                    dayOfWeek = i.toString(),
                    endDate = i.toString(),
                    frequency = i,
                    groups = listOf(
                        Group(
                            id = i.toString(),
                            number = "972101"
                        )
                    ),
                    id = i.toString(),
                    lessonTime = LessonTime(
                        endTime = EndTime(
                            i, 20, 0, 0
                        ),
                        startTime = StartTime(
                            i + 1, 20, 0, 0
                        ),
                        lessonNumber = i
                    ),
                    lessonType = LessonType("10", "Лекция"),
                    startDate = i.toString(),
                    studyRoom = StudyRoom(
                        194, 2, "Лекторий", 332
                    ),
                    subject = Subject(
                        "10",
                        "Математический Анализ"
                    ),
                    teacher = Teacher(
                        firstName = "Даммер",
                        lastName = "Диана",
                        patronymicName = "Даммировна"
                    )
                )
            )
        }
        for (i in 0..5) {
            listOfScheduleForDay.add(
                ScheduleForDay(
                    date = i.toString(),
                    lessons = listOfLessons
                )
            )
        }
        return listOfScheduleForDay
    }

    override suspend fun getScheduleForWeekByGroupId(
        groupId: String,
        startDate: String,
        endDate: String
    ): List<ScheduleForDay> {
        val listOfScheduleForDay = mutableListOf<ScheduleForDay>()
        val listOfLessons = mutableListOf<Lesson>()
        for (i in 2..4) {
            listOfLessons.add(
                Lesson(
                    dayOfWeek = i.toString(),
                    endDate = i.toString(),
                    frequency = i,
                    groups = listOf(
                        Group(
                            id = i.toString(),
                            number = "972101"
                        )
                    ),
                    id = i.toString(),
                    lessonTime = LessonTime(
                        endTime = EndTime(
                            10, 20, 0, 0
                        ),
                        startTime = StartTime(
                            10, 10, 0, 0
                        ),
                        lessonNumber = i
                    ),
                    lessonType = LessonType("10", "Лекция"),
                    startDate = i.toString(),
                    studyRoom = StudyRoom(
                        194, 2, "Лекторий", 332
                    ),
                    subject = Subject(
                        "10",
                        "Математический Анализ"
                    ),
                    teacher = Teacher(
                        firstName = "Даммер",
                        lastName = "Диана",
                        patronymicName = "Даммировна"
                    )
                )
            )
        }
        for (i in 6..8) {
            listOfLessons.add(
                Lesson(
                    dayOfWeek = i.toString(),
                    endDate = i.toString(),
                    frequency = i,
                    groups = listOf(
                        Group(
                            id = i.toString(),
                            number = "972101"
                        )
                    ),
                    id = i.toString(),
                    lessonTime = LessonTime(
                        endTime = EndTime(
                            10, 20, 0, 0
                        ),
                        startTime = StartTime(
                            10, 10, 0, 0
                        ),
                        lessonNumber = i
                    ),
                    lessonType = LessonType("10", "Лекция"),
                    startDate = i.toString(),
                    studyRoom = StudyRoom(
                        194, 2, "Лекторий", 332
                    ),
                    subject = Subject(
                        "10",
                        "Математический Анализ"
                    ),
                    teacher = Teacher(
                        firstName = "Даммер",
                        lastName = "Диана",
                        patronymicName = "Даммировна"
                    )
                )
            )
        }

        for (i in 0..6) {
            listOfScheduleForDay.add(
                ScheduleForDay(
                    date = i.toString(),
                    lessons = if (i == 6) emptyList<Lesson>() else listOfLessons
                )
            )
        }
        return listOfScheduleForDay
    }

    override suspend fun getLessonsTimeDetail(): List<LessonTime> {
        val listOfLesson = mutableListOf<LessonTime>()
        for (i in 0..8) {
            listOfLesson.add(
                LessonTime(
                    endTime = EndTime(
                        i + 1, 20, 0, 0
                    ),
                    startTime = StartTime(
                        i, 20, 0, 0
                    ),
                    lessonNumber = i
                )
            )
        }
        return listOfLesson
    }

}