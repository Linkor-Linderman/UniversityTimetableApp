package com.example.universitytimetableapp.feature_schedule.data.remote.dto

import com.example.universitytimetableapp.feature_schedule.domain.model.StartTime

data class StartTimeDto(
    val hour: Int,
    val minute: Int,
    val nano: Int,
    val second: Int
) {
    fun toStartTime(): StartTime {
        return StartTime(
            hour = hour,
            minute = minute,
            nano = nano,
            second = second
        )
    }
}