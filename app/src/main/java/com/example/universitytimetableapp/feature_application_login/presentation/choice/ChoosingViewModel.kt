package com.example.universitytimetableapp.feature_application_login.presentation.choice

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChoosingViewModel @Inject constructor(

) : ViewModel() {
    var isRoleChosen = mutableStateOf(false)
}