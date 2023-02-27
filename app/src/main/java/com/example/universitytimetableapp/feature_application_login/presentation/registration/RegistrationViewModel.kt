package com.example.universitytimetableapp.feature_application_login.presentation.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.universitytimetableapp.common.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val role: String
    private val groupOrTeacherId: String

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
        if (role == Constants.TEACHER) {
            val fullName = checkNotNull(savedStateHandle[Constants.TEACHER_NAME]).toString().split(" ")
            _surname.value = fullName[0]
            _name.value = fullName[1]
            _patronymic.value = fullName[2]
        }
    }

    private fun correctData() {
        if (_surname.value!!.isNotEmpty() && _name.value!!.isNotEmpty() && _patronymic.value!!.isNotEmpty() &&
            _email.value!!.isNotEmpty() && _password.value!!.isNotEmpty() &&
            _confirmPassword.value!!.isNotEmpty() && _password.value == _confirmPassword.value
        ) {
            _isCorrectData.value = true
        }
    }
}