package com.example.universitytimetableapp.feature_application_login.domain.repository

import com.example.universitytimetableapp.feature_application_login.domain.model.SelectionItem
import kotlinx.coroutines.flow.Flow

interface ChoiceRepository {

    fun getExistingTeachers(): Flow<Result<List<SelectionItem>>>

    fun getExistingGroups(): Flow<Result<List<SelectionItem>>>

    fun getExistingClassroom(): Flow<Result<List<SelectionItem>>>

    fun changeGroup(groupId: String): Flow<Result<Unit>>
}