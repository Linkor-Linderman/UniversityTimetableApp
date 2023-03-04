package com.example.universitytimetableapp.feature_application_login.data.repository

import android.util.Log
import com.example.universitytimetableapp.feature_application_login.data.api.ExistingGroupsTeachersApi
import com.example.universitytimetableapp.feature_application_login.domain.model.GroupsTeachersItem
import com.example.universitytimetableapp.feature_application_login.domain.repository.ExistingGroupsTeachersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ExistingGroupsTeachersRepositoryImpl @Inject constructor(
    private val api: ExistingGroupsTeachersApi
) : ExistingGroupsTeachersRepository {

    override fun getExistingTeachers(): Flow<Result<List<GroupsTeachersItem>>> = flow {
        try {
            val teacherList = api.getExistingTeachers().map { it.toGroupsTeachersItem() }
            emit(Result.success(teacherList))
        }
        catch (e: Exception) {
            Log.e("OPS getExistingTeachers", e.message.toString())
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun getExistingGroups(): Flow<Result<List<GroupsTeachersItem>>> = flow {
        try {
            val groupList = api.getExistingGroups().map { it.toGroupsTeachersItem() }
            emit(Result.success(groupList))
        }
        catch (e: Exception) {
            Log.e("OPS getExistingGroups", e.message.toString())
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)
}