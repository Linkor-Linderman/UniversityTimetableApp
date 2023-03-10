package com.example.universitytimetableapp.feature_profile.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universitytimetableapp.common.Constants
import com.example.universitytimetableapp.common.MessageSource
import com.example.universitytimetableapp.common.Screen
import com.example.universitytimetableapp.feature_profile.domain.model.PasswordChange
import com.example.universitytimetableapp.feature_profile.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getUserAccountUseCase: GetUserAccountUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val clearLocalDataUseCase: ClearLocalDataUseCase,
    private val isPasswordFormatUseCase: IsPasswordFormatUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val messageSource: MessageSource
) : ViewModel() {

    private val _uiState = MutableLiveData(ProfileUiState())
    val uiState: LiveData<ProfileUiState> = _uiState

    private val _isCorrectData = MutableLiveData(false)
    val isCorrectData: LiveData<Boolean> = _isCorrectData

    private val _oldPassword = MutableLiveData("")
    val oldPassword: LiveData<String> = _oldPassword
    fun setOldPassword(value: String) {
        _oldPassword.value = value
        correctData()
    }

    private val _newPassword = MutableLiveData("")
    val newPassword: LiveData<String> = _newPassword
    fun setNewPassword(value: String) {
        _newPassword.value = value
        correctData()
    }

    init {
        getAccount()
    }

    fun showOrCloseDialog() {
        if (_uiState.value!!.isShowDialog) {
            _uiState.value = _uiState.value!!.copy(
                isShowDialog = false
            )
            _oldPassword.value = ""
            _newPassword.value = ""
        }
        else {
            _uiState.value = _uiState.value!!.copy(
                isShowDialog = true
            )
        }
    }

    fun changePassword() {
        _oldPassword.value = _oldPassword.value!!.trim()
        _newPassword.value = _newPassword.value!!.trim()
        if (!isValidInput()) {
            _uiState.value = _uiState.value!!.copy(
                isShowMessage = true
            )
            return
        }
        viewModelScope.launch {
            changePasswordUseCase(
                PasswordChange(
                    oldPassword = _oldPassword.value!!,
                    newPassword = _newPassword.value!!
                )
            ).collect { result ->
                result.onSuccess {
                    showOrCloseDialog()
                }.onFailure {
                    it.message?.let {  text ->
                        _uiState.value = _uiState.value!!.copy(
                            isShowMessage = true,
                            message = text
                        )
                    }
                }
            }
        }
    }

    fun goToNextScreen(case: String) {
        when (case) {
            Constants.REGISTER -> {
                _uiState.value = _uiState.value!!.copy(
                    destinationString = "${Screen.ChoosingScreen.route}/${Constants.REGISTER}"
                )
            }
            Constants.CHANGE_INIT_CHOICE_OR_GUEST -> {
                _uiState.value = _uiState.value!!.copy(
                    destinationString = "${Screen.ChoosingScreen.route}/${Constants.CHANGE_INIT_CHOICE_OR_GUEST}"
                )
            }
            Constants.CHANGE_GROUP -> {
                _uiState.value = _uiState.value!!.copy(
                    destinationString = "${Screen.ChoosingScreen.route}/${Constants.CHANGE_GROUP}?${Constants.EMAIL}=test@mail.ru"
                )
            }
            Constants.EXIT -> {
                logout()
                clearLocalDataUseCase()
                _uiState.value = _uiState.value!!.copy(
                    destinationString = Screen.FirstScreen.route,
                    isExit = true
                )
            }
            Constants.CHOOSE_SCHEDULE -> {
                _uiState.value = _uiState.value!!.copy(
                    destinationString = "${Screen.ChoosingScreen.route}/${Constants.CHOOSE_SCHEDULE}"
                )
            }
            Constants.LOGIN -> {
                _uiState.value = _uiState.value!!.copy(
                    destinationString = Screen.LoginScreen.route
                )
            }
            else -> return
        }
        _uiState.value = _uiState.value!!.copy(
            mayNavigate = true
        )
    }

    fun setDefaultState() {
        _uiState.value = _uiState.value!!.copy(mayNavigate = false, isShowMessage = false)
    }

    private fun correctData() {
        _isCorrectData.value = _oldPassword.value!!.isNotEmpty() && _newPassword.value!!.isNotEmpty()
    }

    private fun getAccount() {
        _uiState.value = ProfileUiState(isLoading = true)
        viewModelScope.launch {
            getUserAccountUseCase().collect { result ->
                result.onSuccess {
                    if (it.role == Constants.TEACHER || it.role == Constants.STUDENT) {
                        _uiState.value = _uiState.value!!.copy(
                            isLoading = false,
                            profile = it,
                            isGuest = false,
                            isStudent = it.role == Constants.STUDENT
                        )
                    }
                    else {
                        _uiState.value = _uiState.value!!.copy(
                            isLoading = false,
                            isGuest = true
                        )
                    }
                }.onFailure {
                    _uiState.value = _uiState.value!!.copy(
                        isLoading = false
                    )
                    it.message?.let {  text ->
                        _uiState.value = _uiState.value!!.copy(
                            isShowMessage = true,
                            message = text
                        )
                    }
                }
            }
        }
    }

    private fun isValidInput() : Boolean {
        if (_newPassword.value!!.length !in 6..64) {
            _uiState.value = _uiState.value!!.copy(
                message = messageSource.getMessage(MessageSource.WRONG_PASSWORD_LENGTH)
            )
            return false
        }
        if (!isPasswordFormatUseCase(_newPassword.value!!)) {
            _uiState.value = _uiState.value!!.copy(
                message = messageSource.getMessage(MessageSource.WRONG_PASSWORD_FORMAT)
            )
            return false
        }
        return true
    }
    private fun logout() {
        viewModelScope.launch {
            logoutUseCase().collect { result ->
                result.onSuccess {}.onFailure {}
            }
        }
    }
}