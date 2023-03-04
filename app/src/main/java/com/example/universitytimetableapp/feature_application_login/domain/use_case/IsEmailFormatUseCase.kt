package com.example.universitytimetableapp.feature_application_login.domain.use_case

import android.util.Patterns

class IsEmailFormatUseCase {
    operator fun invoke(email: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(email).matches()
}