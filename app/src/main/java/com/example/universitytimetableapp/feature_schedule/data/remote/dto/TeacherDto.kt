package com.example.universitytimetableapp.feature_schedule.data.remote.dto

import com.example.universitytimetableapp.feature_schedule.domain.model.Teacher

data class TeacherDto(
    val firstName: String,
    val id: String,
    val lastName: String,
    val patronymicName: String
) {
    fun toTeacher(): Teacher {
        return Teacher(
            firstName = firstName,
            lastName = lastName,
            patronymicName = patronymicName
        )
    }
}