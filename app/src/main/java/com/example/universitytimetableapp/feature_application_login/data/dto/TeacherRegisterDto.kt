package com.example.universitytimetableapp.feature_application_login.data.dto

import com.example.universitytimetableapp.feature_application_login.domain.model.TeacherRegistration

data class TeacherRegisterDto(
    val teacherId: String,
    val email: String,
    val password: String
) {
    companion object {
        fun fromTeacherRegistration(teacherRegistration: TeacherRegistration): TeacherRegisterDto {
            return TeacherRegisterDto(
                teacherId = teacherRegistration.teacherId,
                email = teacherRegistration.email,
                password = teacherRegistration.password
            )
        }
    }
}
