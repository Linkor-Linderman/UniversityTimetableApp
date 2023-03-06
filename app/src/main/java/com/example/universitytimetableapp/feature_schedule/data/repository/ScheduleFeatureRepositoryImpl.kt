package com.example.universitytimetableapp.feature_schedule.data.repository

import com.example.universitytimetableapp.feature_schedule.data.remote.ScheduleApiService
import com.example.universitytimetableapp.feature_schedule.domain.model.LessonTime
import com.example.universitytimetableapp.feature_schedule.domain.model.ScheduleForDay
import com.example.universitytimetableapp.feature_schedule.domain.repository.ScheduleFeatureRepository

class ScheduleFeatureRepositoryImpl(
    val api: ScheduleApiService
) : ScheduleFeatureRepository {
    override suspend fun getScheduleForWeekByTeacherId(
        teacherId: String,
        startDate: String,
        endDate: String
    ): List<ScheduleForDay> {
        return api.getScheduleForWeekByTeacherId(
            teacherId = teacherId,
            startDate = startDate,
            endDate = endDate
        ).map { it.toScheduleForDay() }
    }

    override suspend fun getScheduleForWeekByGroupId(
        groupId: String,
        startDate: String,
        endDate: String
    ): List<ScheduleForDay> {
        return api.getScheduleForWeekByGroupId(
            groupId = groupId,
            startDate = startDate,
            endDate = endDate
        ).map { it.toScheduleForDay() }
    }

    override suspend fun getLessonsTimeDetail(): List<LessonTime> {
        return api.getLessonsTimeDetail().map { it.toLessonTime() }
    }

}