package com.example.universitytimetableapp.feature_schedule.data.remote.dto

import com.example.universitytimetableapp.feature_schedule.domain.model.EndTime

data class EndTimeDto(
    val hour: Int,
    val minute: Int,
    val nano: Int,
    val second: Int
) {
    fun toEndTimeDto(): EndTime {
        return EndTime(
            hour = hour,
            minute = minute,
            nano = nano,
            second = second
        )
    }
}