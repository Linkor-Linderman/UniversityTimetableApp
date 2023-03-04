package com.example.universitytimetableapp.feature_application_login.domain.repository

import com.example.universitytimetableapp.feature_application_login.domain.model.GroupsTeachersItem
import kotlinx.coroutines.flow.Flow

interface ExistingGroupsTeachersRepository {

    fun getExistingTeachers(): Flow<Result<List<GroupsTeachersItem>>>

    fun getExistingGroups(): Flow<Result<List<GroupsTeachersItem>>>
}