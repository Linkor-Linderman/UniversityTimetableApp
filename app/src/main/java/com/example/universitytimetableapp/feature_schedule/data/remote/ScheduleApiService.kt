package com.example.universitytimetableapp.feature_schedule.data.remote

import com.example.universitytimetableapp.feature_schedule.data.remote.dto.LessonTimeDto
import com.example.universitytimetableapp.feature_schedule.data.remote.dto.ScheduleForDayDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ScheduleApiService {

    @GET("/api/v1/schedule/teacher/{id}")
    suspend fun getScheduleForWeekByTeacherId(
        @Path("id") teacherId: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
    ): List<ScheduleForDayDto>

    @GET("/api/v1/schedule/group/{id}")
    suspend fun getScheduleForWeekByGroupId(
        @Path("id") groupId: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
    ): List<ScheduleForDayDto>

    @GET("/api/v1/schedule/study-room/{id}")
    suspend fun getScheduleForWeekByStudyRoomId(
        @Path("id") studyRoomId: String,
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String,
    ): List<ScheduleForDayDto>

    @GET("/api/v1/schedule/lesson-time")
    suspend fun getLessonsTimeDetail(): List<LessonTimeDto>
}