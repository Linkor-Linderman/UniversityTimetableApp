package com.example.universitytimetableapp.feature_schedule.data.repository

import com.example.universitytimetableapp.feature_schedule.data.remote.ScheduleApiService
import com.example.universitytimetableapp.feature_schedule.domain.model.*
import com.example.universitytimetableapp.feature_schedule.domain.repository.ScheduleFeatureRepository

class RepositoryForTest(
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
                    groups = listOf(
                        Group(
                            id = i.toString(),
                            number = "972101"
                        )
                    ),
                    id = i.toString(),
                    lessonTime = LessonTime(
                        endTime = LocalTimeModel(
                            i, 20, 0, 0
                        ),
                        startTime = LocalTimeModel(
                            i + 1, 20, 0, 0
                        ),
                        lessonNumber = i
                    ),
                    lessonType = LessonType("10", "Лекция"),
                    studyRoom = StudyRoom(
                        194, 2, "Лекторий", "332"
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
                    dayOfWeek = "",
                    date = "",
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
        if (startDate == "2023-03-06") {
            for (i in 1..4) {
                listOfLessons.add(
                    Lesson(
                        groups = listOf(
                            Group(
                                id = i.toString(),
                                number = "972101"
                            )
                        ),
                        id = i.toString(),
                        lessonTime = LessonTime(
                            endTime = LocalTimeModel(
                                i, 20, 0, 0
                            ),
                            startTime = LocalTimeModel(
                                i + 1, 20, 0, 0
                            ),
                            lessonNumber = i
                        ),
                        lessonType = LessonType("10", "Лекция"),
                        studyRoom = StudyRoom(
                            194, 2, "Лекторий", "332"
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
        } else {
            for (i in 2..4) {
                listOfLessons.add(
                    Lesson(
                        groups = listOf(
                            Group(
                                id = i.toString(),
                                number = "972101"
                            )
                        ),
                        id = i.toString(),
                        lessonTime = LessonTime(
                            endTime = LocalTimeModel(
                                i + 1, 20, 0, 0
                            ),
                            startTime = LocalTimeModel(
                                i, 10, 0, 0
                            ),
                            lessonNumber = i
                        ),
                        lessonType = LessonType("10", "Лекция"),
                        studyRoom = null,
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
            for (i in 5..5) {
                listOfLessons.add(
                    Lesson(
                        groups = listOf(
                            Group(
                                id = i.toString(),
                                number = "972101"
                            )
                        ),
                        id = i.toString(),
                        lessonTime = LessonTime(
                            endTime = LocalTimeModel(
                                i + 1, 35, 0, 0
                            ),
                            startTime = LocalTimeModel(
                                i, 20, 0, 0
                            ),
                            lessonNumber = i
                        ),
                        lessonType = LessonType("10", "Лекция"),
                        studyRoom = StudyRoom(
                            194, null, "Лекторий", "332"
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
        }
        for (i in 0..6) {
            listOfScheduleForDay.add(
                ScheduleForDay(
                    dayOfWeek = "",
                    date = "",
                    lessons = if (i == 6) emptyList<Lesson>() else listOfLessons
                )
            )
        }
        return listOfScheduleForDay
    }

    override suspend fun getScheduleForWeekByStudyRoomId(
        studyRoomId: String,
        startDate: String,
        endDate: String
    ): List<ScheduleForDay> {
        TODO("Not yet implemented")
    }

    override suspend fun getLessonsTimeDetail(): List<LessonTime> {
        val listOfLesson = mutableListOf<LessonTime>()
        for (i in 0..8) {
            listOfLesson.add(
                LessonTime(
                    endTime = LocalTimeModel(
                        i + 1, 20, 0, 0
                    ),
                    startTime = LocalTimeModel(
                        i, 20, 0, 0
                    ),
                    lessonNumber = i
                )
            )
        }
        return listOfLesson
    }

}