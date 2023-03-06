package com.example.universitytimetableapp.feature_schedule.data.remote.dto

import com.example.universitytimetableapp.feature_schedule.domain.model.Subject

data class SubjectDto(
    val id: String,
    val name: String
) {
    fun toSubject(): Subject {
        return Subject(
            id = id,
            name = name
        )
    }
}