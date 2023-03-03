package com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen

import org.joda.time.DateTime
import org.joda.time.Days

data class ScheduleScreenState(
    var currentDay: DateTime = DateTime(),
    val initialDay: DateTime = DateTime(),
    var monthOfCurrentWeek: Int = currentDay.monthOfYear-1,
    var yearOfCurrentWeek: String = currentDay.year.toString(),
    val currentDaysNumber: List<DateTime> = emptyList()
//Тут еще должно лежать само расписание
)