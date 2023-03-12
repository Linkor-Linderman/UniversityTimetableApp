package com.example.universitytimetableapp.feature_schedule.domain.use_case

import android.annotation.SuppressLint
import com.example.universitytimetableapp.feature_schedule.domain.model.LocalTimeModel
import java.text.SimpleDateFormat
import java.util.*

class CalculateTimeDifferenceUseCase {
    @SuppressLint("SimpleDateFormat")
    operator fun invoke(
        startTime: LocalTimeModel,
        endTime: LocalTimeModel
    ): String {
        val format = SimpleDateFormat("HH:mm")
        val date1: Date = format.parse("${startTime.hour}:${startTime.minute}")
        val date2: Date = format.parse("${endTime.hour}:${endTime.minute}")
        val difference: Long = date2.time - date1.time
        return difference.toString()
    }
}