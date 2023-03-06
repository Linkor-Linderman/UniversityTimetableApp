package com.example.universitytimetableapp.feature_schedule.presentation.detail_class_information_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class DetailClassInformationViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val className: String
    private val classTime: String
    private val classNumber: Int
    private val teacherName: String
    private val classType: String
    private val location: String
    private val groupNumber: Int

    private val _state = mutableStateOf(DetailClassInformationScreenState())
    val state: State<DetailClassInformationScreenState> = _state

    init {
        className = checkNotNull(savedStateHandle["className"])
        classTime = checkNotNull(savedStateHandle["classTime"])
        classNumber = checkNotNull(savedStateHandle["classNumber"])
        teacherName = checkNotNull(savedStateHandle["teacherName"])
        classType = checkNotNull(savedStateHandle["classType"])
        location = checkNotNull(savedStateHandle["location"])
        groupNumber = checkNotNull(savedStateHandle["groupNumber"])

        getCurrentClassInformation(
            className,
            classTime,
            classNumber,
            teacherName,
            classType,
            location,
            groupNumber
        )
    }

    fun getCurrentClassInformation(
        className: String = "",
        classTime: String = "",
        classNumber: Int = 0,
        teacherName: String = "",
        classType: String = "",
        location: String = "",
        groupNumber: Int = 0
    ) {
        _state.value = _state.value.copy(
            className = className,
            classNumber = classNumber,
            classTime = classTime,
            teacherName = teacherName,
            classType = classType,
            location = location,
            groupNumber = groupNumber
        )
    }
}