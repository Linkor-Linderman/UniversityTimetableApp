package com.example.universitytimetableapp.feature_application_login.data.api

import com.example.universitytimetableapp.feature_application_login.data.dto.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface EntranceApi {

    @POST("api/v1/authorisation/sign-in")
    suspend fun signIn(@Body body: CredentialsDto): TokenDto

    @POST("api/v1/authorisation/teacher/sign-up")
    suspend fun registerTeacher(@Body body: TeacherRegisterDto)

    @POST("api/v1/authorisation/student/sign-up")
    suspend fun registerStudent(@Body body: StudentRegisterDto)

    @GET("api/v1/profile/me")
    suspend fun getAccount(): AccountDto
}