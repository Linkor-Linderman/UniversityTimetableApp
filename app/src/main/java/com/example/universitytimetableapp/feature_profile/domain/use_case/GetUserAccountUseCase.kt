package com.example.universitytimetableapp.feature_profile.domain.use_case

import com.example.universitytimetableapp.feature_profile.domain.model.UserAccount
import com.example.universitytimetableapp.feature_profile.domain.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserAccountUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    operator fun invoke(): Flow<Result<UserAccount>> = repository.getUserAccount()
}