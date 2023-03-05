package com.example.universitytimetableapp.feature_application_login.data.repository

import android.util.Log
import com.example.universitytimetableapp.feature_application_login.data.api.ChoiceApi
import com.example.universitytimetableapp.feature_application_login.domain.model.GroupsTeachersItem
import com.example.universitytimetableapp.feature_application_login.domain.repository.ChoiceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ChoiceRepositoryImpl @Inject constructor(
    private val api: ChoiceApi
) : ChoiceRepository {

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

    override fun changeGroup(groupId: String): Flow<Result<Unit>> = flow {
        try {
            api.changeGroup(groupId)
            emit(Result.success(Unit))
        }
        catch (e: Exception) {
            Log.e("OPS changeGroup", e.message.toString())
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)
}