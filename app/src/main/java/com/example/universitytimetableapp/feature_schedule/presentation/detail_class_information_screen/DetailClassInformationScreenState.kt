package com.example.universitytimetableapp.feature_schedule.presentation.detail_class_information_screen

data class DetailClassInformationScreenState (
    var className: String = "",
    var classTime: String = "",
    var classNumber: Int = 0,
    var teacherName: String = "",
    var classType: String = "",
    var location: String = "",
    var groupNumber: Int = 0
)
