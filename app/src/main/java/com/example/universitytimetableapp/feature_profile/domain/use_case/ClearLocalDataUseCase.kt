package com.example.universitytimetableapp.feature_profile.domain.use_case

import com.example.universitytimetableapp.feature_profile.domain.repository.AccountRepository
import javax.inject.Inject

class ClearLocalDataUseCase @Inject constructor(
    private val repository: AccountRepository
) {
    operator fun invoke() {
        repository.clearLocalUserData()
    }
}