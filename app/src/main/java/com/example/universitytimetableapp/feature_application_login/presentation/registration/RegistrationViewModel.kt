package com.example.universitytimetableapp.feature_application_login.presentation.registration

import androidx.lifecycle.*
import com.example.universitytimetableapp.common.Constants
import com.example.universitytimetableapp.common.Screen
import com.example.universitytimetableapp.feature_application_login.domain.model.StudentRegistration
import com.example.universitytimetableapp.feature_application_login.domain.model.TeacherRegistration
import com.example.universitytimetableapp.feature_application_login.domain.model.UserSettings
import com.example.universitytimetableapp.feature_application_login.domain.use_case.IsEmailFormatUseCase
import com.example.universitytimetableapp.feature_application_login.domain.use_case.PutUserSettingsUseCase
import com.example.universitytimetableapp.feature_application_login.domain.use_case.RegisterStudentUseCase
import com.example.universitytimetableapp.feature_application_login.domain.use_case.RegisterTeacherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val isEmailFormatUseCase: IsEmailFormatUseCase,
    private val registerTeacherUseCase: RegisterTeacherUseCase,
    private val registerStudentUseCase: RegisterStudentUseCase,
    private val putUserSettingsUseCase: PutUserSettingsUseCase
) : ViewModel() {
    val role: String
    private val groupOrTeacherId: String
    private val groupOrTeacherName: String

    private val _uiState = MutableLiveData(RegistrationUiState())
    val uiState: LiveData<RegistrationUiState> = _uiState

    private val _isCorrectData = MutableLiveData(false)
    val isCorrectData: LiveData<Boolean> = _isCorrectData

    private val _surname = MutableLiveData("")
    val surname: LiveData<String> = _surname
    fun setSurname(value: String) {
        _surname.value = value
        correctData()
    }

    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name
    fun setName(value: String) {
        _name.value = value
        correctData()
    }

    private val _patronymic = MutableLiveData("")
    val patronymic: LiveData<String> = _patronymic
    fun setPatronymic(value: String) {
        _patronymic.value = value
        correctData()
    }

    private val _email = MutableLiveData("")
    val email: LiveData<String> = _email
    fun setEmail(value: String) {
        _email.value = value
        correctData()
    }

    private val _password = MutableLiveData("")
    val password: LiveData<String> = _password
    fun setPassword(value: String) {
        _password.value = value
        correctData()
    }

    private val _confirmPassword = MutableLiveData("")
    val confirmPassword: LiveData<String> = _confirmPassword
    fun setConfirmPassword(value: String) {
        _confirmPassword.value = value
        correctData()
    }

    init {
        role = checkNotNull(savedStateHandle[Constants.TYPE_USER])
        groupOrTeacherId = checkNotNull(savedStateHandle[Constants.ID])
        groupOrTeacherName = checkNotNull(savedStateHandle[Constants.TEACHER_GROUP_NAME])
        if (role == Constants.TEACHER) {
            val fullName = groupOrTeacherName.split(" ")
            _surname.value = fullName[0]
            _name.value = fullName[1]
            _patronymic.value = fullName[2]
        }
    }

    fun register() {
        if (!isEmailFormatUseCase(_email.value!!)) {
            return
        }
        viewModelScope.launch {
            if (role == Constants.TEACHER) {
                registerTeacherUseCase(
                    TeacherRegistration(
                        teacherId = groupOrTeacherId,
                        email = _email.value!!,
                        password = _password.value!!
                    )
                ).collect { result ->
                    result.onSuccess {
                        saveSettings()
                        goToNextScreen()
                    }.onFailure {

                    }
                }
            } else {
                registerStudentUseCase(
                    StudentRegistration(
                        surname = _surname.value!!,
                        name = _name.value!!,
                        patronymic = _patronymic.value!!,
                        groupId = groupOrTeacherId,
                        email = _email.value!!,
                        password = _password.value!!
                    )
                ).collect { result ->
                    result.onSuccess {
                        saveSettings()
                        goToNextScreen()
                    }.onFailure {

                    }
                }
            }
        }
    }

    fun goToNextScreen() {
        if (_uiState.value!!.isShowDialog) {
            _uiState.value = _uiState.value!!.copy(
                isShowDialog = false,
                mayNavigate = true,
                destinationString = "${Screen.ScheduleScreen.route}/$role/$groupOrTeacherId/$groupOrTeacherName"
            )
        }
        else {
            _uiState.value = _uiState.value!!.copy(isShowDialog = true)
        }
    }

    fun setDefaultState() {
        _uiState.value = _uiState.value!!.copy(mayNavigate = false)
    }

    private fun correctData() {
        _isCorrectData.value = _surname.value!!.isNotEmpty() && _name.value!!.isNotEmpty() && _patronymic.value!!.isNotEmpty() &&
                _email.value!!.isNotEmpty() && _password.value!!.isNotEmpty() &&
                _confirmPassword.value!!.isNotEmpty() && _password.value == _confirmPassword.value
    }

    private fun saveSettings() {
        putUserSettingsUseCase(UserSettings(
            role = role,
            idChosenItem = groupOrTeacherId,
            nameChosenItem = groupOrTeacherName
        ))
    }
}