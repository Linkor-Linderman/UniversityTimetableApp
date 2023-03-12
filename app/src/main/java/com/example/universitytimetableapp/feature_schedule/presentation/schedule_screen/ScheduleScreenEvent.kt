package com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen

import org.joda.time.DateTime

sealed class ScheduleScreenEvent {
    data class ChangeCurrentDay(val newDay: DateTime) : ScheduleScreenEvent()
    object ChangeToNextWeek : ScheduleScreenEvent()
    object ChangeToPreviousWeek : ScheduleScreenEvent()
}
