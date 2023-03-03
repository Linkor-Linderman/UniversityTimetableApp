package com.example.universitytimetableapp.feature_schedule.presentation.detail_class_information_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class DetailClassInformationViewModel: ViewModel() {

    private val _state = mutableStateOf(DetailClassInformationScreenState())
    val state: State<DetailClassInformationScreenState> = _state

    fun getCurrentClassInformation(){
        _state.value = _state.value.copy(
            className = "Математический анализ",
            classNumber = 1,
            classTime = "14:45 - 16:20 ",
            teacherName = "Даммер Диана Дамировна",
            classType = "Лекция",
            location = "332 (2) Учебная аудитория",
            groupNumber = 972101
        )
    }
}