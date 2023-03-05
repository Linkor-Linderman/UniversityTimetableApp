package com.example.universitytimetableapp.feature_application_login.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universitytimetableapp.common.Constants
import com.example.universitytimetableapp.common.Screen
import com.example.universitytimetableapp.feature_application_login.domain.model.LoginCredentials
import com.example.universitytimetableapp.feature_application_login.domain.model.UserSettings
import com.example.universitytimetableapp.feature_application_login.domain.use_case.GetAccountInfoUseCase
import com.example.universitytimetableapp.feature_application_login.domain.use_case.IsEmailFormatUseCase
import com.example.universitytimetableapp.feature_application_login.domain.use_case.LoginUseCase
import com.example.universitytimetableapp.feature_application_login.domain.use_case.PutUserSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val isEmailFormatUseCase: IsEmailFormatUseCase,
    private val loginUseCase: LoginUseCase,
    private val getAccountInfoUseCase: GetAccountInfoUseCase,
    private val putUserSettingsUseCase: PutUserSettingsUseCase,
) : ViewModel() {
    private val _uiState = MutableLiveData(LoginUiState())
    val uiState: LiveData<LoginUiState> = _uiState

    private val _isCorrectData = MutableLiveData(false)
    val isCorrectData: LiveData<Boolean> = _isCorrectData

    private val _login = MutableLiveData("")
    val login: LiveData<String> = _login
    fun setLogin(value: String) {
        _login.value = value
        correctData()
    }

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password
    fun setPassword(value: String) {
        _password.value = value
        correctData()
    }

    fun login() {
        if (!isEmailFormatUseCase(_login.value!!)) {
            return
        }
        viewModelScope.launch {
            loginUseCase(LoginCredentials(_login.value!!, _password.value!!)).collect { result ->
                result.onSuccess {
                    getAccountInfo()
                }.onFailure {

                }
            }
        }
    }

    fun setDefaultState() {
        _uiState.value = _uiState.value!!.copy(mayNavigate = false)
    }

    private fun getAccountInfo() {
        viewModelScope.launch {
            getAccountInfoUseCase().collect { result ->
                result.onSuccess {
                    val userSettings =
                        when (it.role) {
                            Constants.TEACHER -> {
                                UserSettings(
                                    role = it.role,
                                    idChosenItem = it.teacherId,
                                    nameChosenItem = it.full_name
                                )
                            }
                            Constants.STUDENT -> {
                                UserSettings(
                                    role = it.role,
                                    idChosenItem = it.group!!.id,
                                    nameChosenItem = it.group.number
                                )
                            }
                            else -> {
                                _uiState.value = _uiState.value!!.copy(
                                    isShowMessage = true,
                                    message = "Адмнистраторы и составители заходите в приложение под гостем"
                                    //Тут можно автоматически послать на экран выбора роли
                                )
                                return@onSuccess
                            }
                        }
                    putUserSettingsUseCase(userSettings)
                    _uiState.value = _uiState.value!!.copy(
                        mayNavigate = true,
                        destinationString =
                        "${Screen.ScheduleScreen.route}/${userSettings.role}/${userSettings.idChosenItem}/${userSettings.nameChosenItem}"
                    )
                }.onFailure {

                }
            }
        }
    }

    private fun correctData() {
        _isCorrectData.value = _login.value!!.isNotEmpty() && _password.value!!.isNotEmpty()
    }
}