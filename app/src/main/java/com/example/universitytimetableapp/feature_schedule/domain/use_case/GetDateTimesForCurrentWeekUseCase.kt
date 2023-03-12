package com.example.universitytimetableapp.feature_schedule.domain.use_case

import org.joda.time.DateTime

class GetDateTimesForCurrentWeekUseCase {

    operator fun invoke(currentDay: DateTime): List<DateTime> {
        val listNumbersOfAWeek = mutableListOf<DateTime>()
        for (i in 1..7){
            listNumbersOfAWeek.add(currentDay.withDayOfWeek(i))
        }
        return listNumbersOfAWeek
    }
}