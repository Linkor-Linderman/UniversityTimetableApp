package com.example.universitytimetableapp.feature_application_login.domain.use_case

import com.example.universitytimetableapp.feature_application_login.domain.model.StudentRegistration
import com.example.universitytimetableapp.feature_application_login.domain.repository.EntranceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterStudentUseCase @Inject constructor(
    private val repository: EntranceRepository
) {
    operator fun invoke(studentRegistration: StudentRegistration): Flow<Result<Unit>> =
        repository.registerStudent(studentRegistration)
}