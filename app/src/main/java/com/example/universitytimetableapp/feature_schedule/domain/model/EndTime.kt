package com.example.universitytimetableapp.feature_schedule.domain.model

data class EndTime(
    val hour: Int,
    val minute: Int,
    val nano: Int,
    val second: Int
) {
    fun getHourAndMinute(): String {
        return "$hour:$minute"
    }
}