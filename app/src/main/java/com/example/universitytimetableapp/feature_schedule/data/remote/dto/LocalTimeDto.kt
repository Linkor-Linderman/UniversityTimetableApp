package com.example.universitytimetableapp.feature_schedule.data.remote.dto

import com.example.universitytimetableapp.feature_schedule.domain.model.LocalTimeModel

data class LocalTimeDto(
    val hour: Int,
    val minute: Int,
    val nano: Int?,
    val second: Int?
) {
    fun toLocalTimeModel(): LocalTimeModel {
        return LocalTimeModel(
            hour, minute, nano, second
        )
    }
}