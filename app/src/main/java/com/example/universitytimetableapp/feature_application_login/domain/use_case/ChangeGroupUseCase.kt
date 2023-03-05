package com.example.universitytimetableapp.feature_application_login.domain.use_case

import com.example.universitytimetableapp.feature_application_login.domain.repository.ChoiceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChangeGroupUseCase @Inject constructor(
    private val repository: ChoiceRepository
) {
    operator fun invoke(groupId: String): Flow<Result<Unit>> = repository.changeGroup(groupId)
}