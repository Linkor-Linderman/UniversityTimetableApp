package com.example.universitytimetableapp.feature_application_login.domain.use_case

import com.example.universitytimetableapp.common.Constants
import com.example.universitytimetableapp.feature_application_login.domain.model.GroupsTeachersItem
import com.example.universitytimetableapp.feature_application_login.domain.repository.ExistingGroupsTeachersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSelectionListUseCase @Inject constructor(
    private val repository: ExistingGroupsTeachersRepository
) {
    operator fun invoke(role: String): Flow<Result<List<GroupsTeachersItem>>> {
        return if (role == Constants.TEACHER) {
            repository.getExistingTeachers()
        }
        else {
            repository.getExistingGroups()
        }
    }
}