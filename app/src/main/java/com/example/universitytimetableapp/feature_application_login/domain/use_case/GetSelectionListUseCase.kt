package com.example.universitytimetableapp.feature_application_login.domain.use_case

import com.example.universitytimetableapp.common.Constants
import com.example.universitytimetableapp.feature_application_login.domain.model.SelectionItem
import com.example.universitytimetableapp.feature_application_login.domain.repository.ChoiceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectionListUseCase @Inject constructor(
    private val repository: ChoiceRepository
) {
    operator fun invoke(role: String): Flow<Result<List<SelectionItem>>> {
        return when (role) {
            Constants.TEACHER -> {
                repository.getExistingTeachers()
            }
            Constants.STUDENT -> {
                repository.getExistingGroups()
            }
            else -> {
                repository.getExistingClassroom()
            }
        }
    }
}