package com.example.universitytimetableapp.feature_application_login.data.repository

import android.util.Log
import com.example.universitytimetableapp.common.Constants
import com.example.universitytimetableapp.common.SharedPref
import com.example.universitytimetableapp.feature_application_login.data.api.EntranceApi
import com.example.universitytimetableapp.feature_application_login.data.dto.CredentialsDto
import com.example.universitytimetableapp.feature_application_login.data.dto.StudentRegisterDto
import com.example.universitytimetableapp.feature_application_login.data.dto.TeacherRegisterDto
import com.example.universitytimetableapp.feature_application_login.domain.model.LoginCredentials
import com.example.universitytimetableapp.feature_application_login.domain.model.StudentRegistration
import com.example.universitytimetableapp.feature_application_login.domain.model.TeacherRegistration
import com.example.universitytimetableapp.feature_application_login.domain.repository.EntranceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EntranceRepositoryImpl @Inject constructor(
    private val api: EntranceApi,
    private val sharedPref: SharedPref
) : EntranceRepository {

    override fun signIn(body: LoginCredentials): Flow<Result<Unit>> = flow {
        try {
            val token = api.signIn(CredentialsDto.fromLoginCredentials(body))
            sharedPref.setString(Constants.TOKEN, token.token)
            emit(Result.success(Unit))
        }
        catch (e: Exception) {
            Log.e("OPS signIn", e.message.toString())
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun registerTeacher(body: TeacherRegistration): Flow<Result<Unit>> = flow {
        try {
            api.registerTeacher(TeacherRegisterDto.fromTeacherRegistration(body))
            emit(Result.success(Unit))
        }
        catch (e: Exception) {
            Log.e("OPS registerTeacher", e.message.toString())
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun registerStudent(body: StudentRegistration): Flow<Result<Unit>> = flow {
        try {
            api.registerStudent(StudentRegisterDto.fromStudentRegistration(body))
            emit(Result.success(Unit))
        }
        catch (e: Exception) {
            Log.e("OPS registerStudent", e.message.toString())
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)
}