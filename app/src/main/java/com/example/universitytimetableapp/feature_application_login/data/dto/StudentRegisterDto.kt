package com.example.universitytimetableapp.feature_application_login.data.dto

import com.example.universitytimetableapp.feature_application_login.domain.model.StudentRegistration

data class StudentRegisterDto(
    val firstName: String,
    val lastName: String,
    val patronymicName: String?,
    val groupId: String,
    val email: String,
    val password: String
) {
    companion object {
        fun fromStudentRegistration(studentRegistration: StudentRegistration): StudentRegisterDto {
            return StudentRegisterDto(
                firstName = studentRegistration.name,
                lastName = studentRegistration.surname,
                patronymicName =  studentRegistration.patronymic,
                groupId = studentRegistration.groupId,
                email = studentRegistration.email,
                password = studentRegistration.password
            )
        }
    }
}
