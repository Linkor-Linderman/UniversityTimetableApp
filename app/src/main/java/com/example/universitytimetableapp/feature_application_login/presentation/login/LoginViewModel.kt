package com.example.universitytimetableapp.feature_application_login.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

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

    private fun correctData() {
        if (_login.value!!.isNotEmpty() && _password.value!!.isNotEmpty()) {
            _isCorrectData.value = true
        }
    }
}