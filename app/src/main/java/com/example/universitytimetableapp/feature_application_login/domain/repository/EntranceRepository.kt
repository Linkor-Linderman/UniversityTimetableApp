package com.example.universitytimetableapp.feature_application_login.domain.repository

import com.example.universitytimetableapp.feature_application_login.domain.model.LoginCredentials
import com.example.universitytimetableapp.feature_application_login.domain.model.StudentRegistration
import com.example.universitytimetableapp.feature_application_login.domain.model.TeacherRegistration
import kotlinx.coroutines.flow.Flow

interface EntranceRepository {

    fun signIn(body: LoginCredentials): Flow<Result<Unit>>

    fun registerTeacher(body: TeacherRegistration): Flow<Result<Unit>>

    fun registerStudent(body: StudentRegistration): Flow<Result<Unit>>
}