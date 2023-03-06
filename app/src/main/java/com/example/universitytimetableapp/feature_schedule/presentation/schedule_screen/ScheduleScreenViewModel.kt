package com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.universitytimetableapp.common.Resource
import com.example.universitytimetableapp.feature_schedule.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter
import javax.inject.Inject


@HiltViewModel
class ScheduleScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val useCases: UseCases
) : ViewModel() {

    private val type: String
    private val id: String
    private val name: String

    private val _state = mutableStateOf(ScheduleScreenState())
    val state: State<ScheduleScreenState> = _state

    init {
        type = checkNotNull(savedStateHandle["typeUser"])
        id = checkNotNull(savedStateHandle["id"])
        name = checkNotNull(savedStateHandle["teacherName"])

        getCurrentDaysOfMonthForWeek()

        getScheduleForWeek(
            type = type,
            id = id,
            name = name
        )

    }

    private fun getCurrentDaysOfMonthForWeek() {
        _state.value = _state.value.copy(
            currentDaysNumber = useCases.getDayNumbersOfAWeek(_state.value.currentDay)
        )
    }

    private fun getScheduleForWeek(
        type: String,
        id: String,
        name: String
    ) {
        val fmt: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd")
        val startDate = fmt.parseDateTime(_state.value.currentDaysNumber[0].toString())
        val endDate =
            fmt.parseDateTime(_state.value.currentDaysNumber[_state.value.currentDaysNumber.size - 1].toString())
        when (type) {
            "STUDENT" -> {
                useCases.getScheduleForWeekByGroupId(
                    id = id,
                    startDate = startDate.toString(),
                    endDate = endDate.toString()
                ).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                scheduleItemsForWeek = result.data ?: emptyList(),
                                type = "STUDENT",
                                id = id,
                                name = name
                            )
                        }
                        is Resource.Error -> {
                            _state.value = _state.value.copy(
                                errorMessage = result.message ?: "An unexpected error occured"
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = _state.value.copy(
                                isLoading = true
                            )
                        }
                    }
                }
            }
            "TEACHER" -> {
                useCases.getScheduleForWeekByTeacherId(
                    id = id,
                    startDate = startDate.toString(),
                    endDate = endDate.toString()
                ).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                scheduleItemsForWeek = result.data ?: emptyList(),
                                type = "TEACHER",
                                id = id,
                                name = name
                            )
                        }
                        is Resource.Error -> {
                            _state.value = _state.value.copy(
                                errorMessage = result.message ?: "An unexpected error occured"
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = _state.value.copy(
                                isLoading = true
                            )
                        }
                    }
                }
            }
        }
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
                val mondayOfNextWeek = useCases.getNextMondayUseCase(_state.value.currentDay)
                _state.value = _state.value.copy(
                    currentDay = mondayOfNextWeek,
                    monthOfCurrentWeek = mondayOfNextWeek.monthOfYear - 1,
                    yearOfCurrentWeek = mondayOfNextWeek.year.toString(),
                    currentDaysNumber = useCases.getDayNumbersOfAWeek(mondayOfNextWeek),
                )
                Log.i("MONDAY OF NEXT WEEK", _state.value.currentDay.toString())
            }
            is ScheduleScreenEvent.ChangeToPreviousWeek -> {
                val mondayOfPreviousWeek =
                    useCases.getPreviousMondayUseCase(_state.value.currentDay)
                _state.value = _state.value.copy(
                    currentDay = mondayOfPreviousWeek,
                    monthOfCurrentWeek = mondayOfPreviousWeek.monthOfYear - 1,
                    yearOfCurrentWeek = mondayOfPreviousWeek.year.toString(),
                    currentDaysNumber = useCases.getDayNumbersOfAWeek(mondayOfPreviousWeek)
                )
                Log.i("MONDAY OF PREVIOUS WEEK", _state.value.currentDay.toString())
            }
        }
    }
}