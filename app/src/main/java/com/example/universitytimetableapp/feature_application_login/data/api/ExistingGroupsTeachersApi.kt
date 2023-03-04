package com.example.universitytimetableapp.feature_application_login.data.api

import com.example.universitytimetableapp.feature_application_login.data.dto.GroupDto
import com.example.universitytimetableapp.feature_application_login.data.dto.TeacherDto
import retrofit2.http.GET

interface ExistingGroupsTeachersApi {

    @GET("api/v1/teacher")
    suspend fun getExistingTeachers(): List<TeacherDto>

    @GET("api/v1/group")
    suspend fun getExistingGroups(): List<GroupDto>
}