package com.example.universitytimetableapp.feature_schedule.presentation.detail_class_information_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.universitytimetableapp.common.Constants

class DetailClassInformationViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val className: String
    private val classTime: String
    private val classNumber: String
    private val teacherName: String
    private val classType: String
    private val location: String
    private val groupNumber: String

    private val _state = mutableStateOf(DetailClassInformationScreenState())
    val state: State<DetailClassInformationScreenState> = _state

    init {
        className = checkNotNull(savedStateHandle[Constants.CLASS_NAME])
        classTime = checkNotNull(savedStateHandle[Constants.CLASS_TIME])
        classNumber = checkNotNull(savedStateHandle[Constants.CLASS_NUMBER])
        teacherName = checkNotNull(savedStateHandle[Constants.TEACHER_NAME])
        classType = checkNotNull(savedStateHandle[Constants.CLASS_TYPE])
        location = checkNotNull(savedStateHandle[Constants.LOCATION])
        groupNumber = checkNotNull(savedStateHandle[Constants.GROUP_NUMBER])

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

    private fun getCurrentClassInformation(
        className: String = "",
        classTime: String = "",
        classNumber: String = "",
        teacherName: String = "",
        classType: String = "",
        location: String = "",
        groupNumber: String = ""
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