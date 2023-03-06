package com.example.universitytimetableapp.feature_schedule.domain.repository

import com.example.universitytimetableapp.feature_schedule.domain.model.LessonTime
import com.example.universitytimetableapp.feature_schedule.domain.model.ScheduleForDay

interface ScheduleFeatureRepository {

    suspend fun getScheduleForWeekByTeacherId(
        teacherId: String,
        startDate: String,
        endDate: String
    ): List<ScheduleForDay>

    suspend fun getScheduleForWeekByGroupId(
        groupId: String,
        startDate: String,
        endDate: String
    ): List<ScheduleForDay>

    suspend fun getLessonsTimeDetail(): List<LessonTime>

}