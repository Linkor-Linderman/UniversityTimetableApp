package com.example.universitytimetableapp.feature_schedule.domain.model

data class LocalTimeModel(
    val hour: Int,
    val minute: Int,
    val nano: Int?,
    val second: Int?
) {
    fun getHourAndMinute(): String {
        val formatHour =
            if (hour.toString().length == 1) {
                "0$hour"
            } else {
                hour.toString()
            }
        val formatMinute =
            if (minute.toString().length == 1) {
                "0$minute"
            } else {
                minute.toString()
            }
        return "$formatHour:$formatMinute"
    }
}

