package com.example.universitytimetableapp.feature_schedule.domain.use_case

import org.joda.time.DateTime

class GetNextMondayUseCase {

    operator fun invoke(currentDay: DateTime): DateTime{

        val old: Int = currentDay.dayOfWeek
        var monday = 1

        if (monday <= old) {
            monday += 7
        }
        return currentDay.plusDays(monday - old)
    }
}