package com.example.universitytimetableapp.feature_profile.domain.repository

import com.example.universitytimetableapp.feature_profile.domain.model.PasswordChange
import com.example.universitytimetableapp.feature_profile.domain.model.UserAccount
import kotlinx.coroutines.flow.Flow

interface AccountRepository {

    fun getUserAccount(): Flow<Result<UserAccount>>

    fun changePassword(passwordChange: PasswordChange): Flow<Result<Unit>>

    fun clearLocalUserData()
}