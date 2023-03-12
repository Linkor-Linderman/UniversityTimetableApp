package com.example.universitytimetableapp.feature_application_login.presentation.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.universitytimetableapp.common.Screen
import com.example.universitytimetableapp.feature_application_login.domain.use_case.GetUserSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirstViewModel @Inject constructor(
    getUserSettingsUseCase: GetUserSettingsUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData(FirstUiState())
    val uiState: LiveData<FirstUiState> = _uiState

    init {
        _uiState.value = FirstUiState(
            isLoading = true
        )
        val userSettings = getUserSettingsUseCase()
        userSettings.let {
            val isAlreadyInitUser = it.role.isNotEmpty() && it.idChosenItem.isNotEmpty() &&
                    it.nameChosenItem.isNotEmpty()
            if (isAlreadyInitUser) {
                _uiState.value = _uiState.value!!.copy(
                    mayGoSchedule = true,
                    destinationString = "${Screen.ScheduleScreen.route}/${it.role}/${it.idChosenItem}/${it.nameChosenItem}"
                )
            }
            else {
                _uiState.value = _uiState.value!!.copy(
                    isLoading = false
                )
            }
        }
    }

    fun setDefaultState() {
        _uiState.value = _uiState.value!!.copy(
            isLoading = false,
            mayGoSchedule = false
        )
    }
}