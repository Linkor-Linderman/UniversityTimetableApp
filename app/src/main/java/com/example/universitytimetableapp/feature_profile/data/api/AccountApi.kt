package com.example.universitytimetableapp.feature_profile.data.api

import com.example.universitytimetableapp.feature_profile.data.dto.AccountDto
import com.example.universitytimetableapp.feature_profile.data.dto.PasswordModifyDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

interface AccountApi {

    @GET("api/v1/profile/me")
    suspend fun getAccount(): AccountDto

    @PUT("api/v1/profile/security/password")
    suspend fun changePassword(@Body body: PasswordModifyDto)
}