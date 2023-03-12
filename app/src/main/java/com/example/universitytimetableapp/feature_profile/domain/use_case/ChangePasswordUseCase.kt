package com.example.universitytimetableapp.feature_profile.domain.use_case

import com.example.universitytimetableapp.feature_profile.domain.model.PasswordChange
import com.example.universitytimetableapp.feature_profile.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangePasswordUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    operator fun invoke(passwordChange: PasswordChange): Flow<Result<Unit>> =
        repository.changePassword(passwordChange)
}