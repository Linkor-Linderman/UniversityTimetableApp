package com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen

import com.example.universitytimetableapp.feature_schedule.domain.model.ScheduleItemsForDay
import org.joda.time.DateTime

data class ScheduleScreenState(
    var currentDay: DateTime = DateTime(),
    val initialDay: DateTime = DateTime(),
    var monthOfCurrentWeek: Int = currentDay.monthOfYear-1,
    var yearOfCurrentWeek: String = currentDay.year.toString(),
    val currentDaysNumber: List<DateTime> = emptyList(),
    val scheduleItemsForWeek: List<ScheduleItemsForDay> = emptyList(),
    val type: String = "",
    val id: String = "",
    val name: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String = ""
)