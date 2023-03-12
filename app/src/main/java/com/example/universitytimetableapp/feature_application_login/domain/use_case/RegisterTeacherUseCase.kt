package com.example.universitytimetableapp.feature_application_login.domain.use_case

import com.example.universitytimetableapp.feature_application_login.domain.model.TeacherRegistration
import com.example.universitytimetableapp.feature_application_login.domain.repository.EntranceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterTeacherUseCase @Inject constructor(
    private val repository: EntranceRepository
) {
    operator fun invoke(teacherRegistration: TeacherRegistration): Flow<Result<Unit>> =
        repository.registerTeacher(teacherRegistration)
}