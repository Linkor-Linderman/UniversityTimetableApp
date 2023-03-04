package com.example.universitytimetableapp.feature_application_login.domain.use_case

import com.example.universitytimetableapp.feature_application_login.domain.model.LoginCredentials
import com.example.universitytimetableapp.feature_application_login.domain.repository.EntranceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: EntranceRepository
) {
    operator fun invoke(loginCredentials: LoginCredentials): Flow<Result<Unit>> =
        repository.signIn(loginCredentials)
}