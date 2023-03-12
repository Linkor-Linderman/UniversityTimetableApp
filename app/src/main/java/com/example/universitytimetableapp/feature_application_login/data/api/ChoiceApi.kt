package com.example.universitytimetableapp.feature_application_login.data.api

import com.example.universitytimetableapp.feature_application_login.data.dto.GroupDto
import com.example.universitytimetableapp.feature_application_login.data.dto.StudyRoomDto
import com.example.universitytimetableapp.feature_application_login.data.dto.TeacherDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface ChoiceApi {

    @GET("api/v1/teacher")
    suspend fun getExistingTeachers(): List<TeacherDto>

    @GET("api/v1/group")
    suspend fun getExistingGroups(): List<GroupDto>

    @GET("api/v1/study-room")
    suspend fun getExistingClassroom(): List<StudyRoomDto>

    @PUT("api/v1/profile/group")
    suspend fun changeGroup(@Body groupId: String)
}