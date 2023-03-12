package com.example.universitytimetableapp.feature_application_login.data.repository

import com.example.universitytimetableapp.feature_application_login.data.dto.ErrorDto
import com.google.gson.GsonBuilder
import retrofit2.HttpException
import java.io.IOException

fun catchError(e: Exception): Throwable {
    return when(e) {
        is HttpException -> {
            var error: ErrorDto? = null
            e.response()?.errorBody()?.string()?.let {
                error = GsonBuilder().create().fromJson(it, ErrorDto::class.java)
            }
            Throwable(error?.message?: "Http Error ${e.code()}")
        }
        is IOException -> Throwable("Check your internet connection")
        else -> e
    }
}