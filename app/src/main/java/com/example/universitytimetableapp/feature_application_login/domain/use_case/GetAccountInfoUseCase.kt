package com.example.universitytimetableapp.feature_application_login.domain.use_case

import com.example.universitytimetableapp.feature_application_login.domain.model.AccountInfo
import com.example.universitytimetableapp.feature_application_login.domain.repository.EntranceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAccountInfoUseCase @Inject constructor(
    private val repository: EntranceRepository
) {
    operator fun invoke(): Flow<Result<AccountInfo>> = repository.getAccountInfo()
}