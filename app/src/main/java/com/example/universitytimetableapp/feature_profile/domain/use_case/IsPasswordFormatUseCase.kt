package com.example.universitytimetableapp.feature_profile.domain.use_case

import javax.inject.Inject

class IsPasswordFormatUseCase @Inject constructor() {
    operator fun invoke(password: String): Boolean {
        val regex = Regex("^(?=.*\\d)(?=.*[a-zA-Z]).*")
        return regex.matches(password)
    }
}