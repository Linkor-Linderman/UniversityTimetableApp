package com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.universitytimetableapp.feature_schedule.domain.use_case.GetDateTimesForCurrentWeekUseCase
import com.example.universitytimetableapp.feature_schedule.domain.use_case.GetNextMondayUseCase
import com.example.universitytimetableapp.feature_schedule.domain.use_case.GetPreviousMondayUseCase
import com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.ScheduleScreenEvent
import com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.ScheduleScreenState

class ScheduleScreenViewModel : ViewModel() {

    private val getDayNumbersOfAWeek = GetDateTimesForCurrentWeekUseCase()
    private val getNextMondayUseCase = GetNextMondayUseCase()
    private val getPreviousMondayUseCase = GetPreviousMondayUseCase()

    private val _state = mutableStateOf(ScheduleScreenState())
    val state: State<ScheduleScreenState> = _state

    init {
        getCurrentDaysOfMonthForWeek()
    }

    private fun getCurrentDaysOfMonthForWeek() {
        _state.value = _state.value.copy(
            currentDaysNumber = getDayNumbersOfAWeek(_state.value.currentDay)
        )
    }

    fun onEvent(event: ScheduleScreenEvent) {
        when (event) {
            is ScheduleScreenEvent.ChangeCurrentDay -> {
                _state.value = _state.value.copy(
                    currentDay = event.newDay,
                    monthOfCurrentWeek = event.newDay.monthOfYear - 1,
                    yearOfCurrentWeek = event.newDay.year.toString(),
                )
                Log.i("CURRENT DAY ON CHANGE", _state.value.currentDay.toString())
            }
            is ScheduleScreenEvent.ChangeToNextWeek -> {
                val mondayOfNextWeek = getNextMondayUseCase(_state.value.currentDay)
                _state.value = _state.value.copy(
                    currentDay = mondayOfNextWeek,
                    monthOfCurrentWeek = mondayOfNextWeek.monthOfYear - 1,
                    yearOfCurrentWeek = mondayOfNextWeek.year.toString(),
                    currentDaysNumber = getDayNumbersOfAWeek(mondayOfNextWeek),
                )
                Log.i("MONDAY OF NEXT WEEK", _state.value.currentDay.toString())
            }
            is ScheduleScreenEvent.ChangeToPreviousWeek -> {
                val mondayOfPreviousWeek = getPreviousMondayUseCase(_state.value.currentDay)
                _state.value = _state.value.copy(
                    currentDay = mondayOfPreviousWeek,
                    monthOfCurrentWeek = mondayOfPreviousWeek.monthOfYear - 1,
                    yearOfCurrentWeek = mondayOfPreviousWeek.year.toString(),
                    currentDaysNumber = getDayNumbersOfAWeek(mondayOfPreviousWeek)
                )
                Log.i("MONDAY OF PREVIOUS WEEK", _state.value.currentDay.toString())
            }
        }
    }
}