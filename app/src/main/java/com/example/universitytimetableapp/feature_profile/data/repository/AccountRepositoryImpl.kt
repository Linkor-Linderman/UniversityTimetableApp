package com.example.universitytimetableapp.feature_profile.data.repository

import android.util.Log
import com.example.universitytimetableapp.common.SharedPref
import com.example.universitytimetableapp.feature_profile.data.api.AccountApi
import com.example.universitytimetableapp.feature_profile.data.dto.PasswordModifyDto
import com.example.universitytimetableapp.feature_profile.domain.model.PasswordChange
import com.example.universitytimetableapp.feature_profile.domain.model.UserAccount
import com.example.universitytimetableapp.feature_profile.domain.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val api: AccountApi,
    private val sharedPref: SharedPref
) : AccountRepository {

    override fun getUserAccount(): Flow<Result<UserAccount>> = flow {
        try {
            val account = api.getAccount().toUserAccount()
            emit(Result.success(account))
        }
        catch (e: HttpException) {
            if (e.code() == 401 || e.code() == 403) {
                emit(Result.failure(Throwable()))
            }
            else {
                emit(Result.failure(e))
            }
        }
        catch (e: Exception) {
            Log.e("OPS getUserAccount", e.message.toString())
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun changePassword(passwordChange: PasswordChange): Flow<Result<Unit>> = flow {
        try {
            api.changePassword(PasswordModifyDto.fromPasswordChange(passwordChange))
            emit(Result.success(Unit))
        }
        catch (e: Exception) {
            Log.e("OPS changePassword", e.message.toString())
            emit(Result.failure(catchError(e)))
        }
    }.flowOn(Dispatchers.IO)

    override fun logout(): Flow<Result<Unit>> = flow {
        try {
            api.logout()
            emit(Result.success(Unit))
        }
        catch (e: Exception) {
            Log.e("OPS logout", e.message.toString())
            emit(Result.failure(e))
        }
    }.flowOn(Dispatchers.IO)

    override fun clearLocalUserData() {
        sharedPref.clearSettings()
    }

}