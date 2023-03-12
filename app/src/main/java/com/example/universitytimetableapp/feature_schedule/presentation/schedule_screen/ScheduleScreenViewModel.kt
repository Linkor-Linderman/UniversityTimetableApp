package com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universitytimetableapp.common.Constants
import com.example.universitytimetableapp.common.Resource
import com.example.universitytimetableapp.feature_schedule.domain.model.ScheduleItemsForDay
import com.example.universitytimetableapp.feature_schedule.domain.use_case.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
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
        type = checkNotNull(savedStateHandle[Constants.TYPE_USER])
        id = checkNotNull(savedStateHandle[Constants.ID])
        name = checkNotNull(savedStateHandle[Constants.TEACHER_GROUP_NAME])

        getCurrentDaysOfMonthForWeek()

        getScheduleForWeek(
            type = type,
            id = id,
            name = name
        )

    }

    fun refresh() = viewModelScope.launch {
        _state.value = _state.value.copy(
            errorMessage = "",
            isRefreshing = true,
            scheduleItemsForWeek = emptyList()
        )
        getScheduleForWeek(
            type = type,
            id = id,
            name = name
        )
        _state.value = _state.value.copy(
            isRefreshing = false
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
        val startDate =
            fmt.parseDateTime(_state.value.currentDaysNumber[0].toLocalDate().toString()).toString()
                .substring(0, 10)

        val endDate =
            fmt.parseDateTime(
                _state.value.currentDaysNumber[_state.value.currentDaysNumber.size - 1].toLocalDate()
                    .toString()
            ).toString().substring(0, 10)

        _state.value = _state.value.copy(
            type = type,
            id = id,
            name = name
        )

        when (type) {
            Constants.STUDENT -> {
                useCases.getScheduleForWeekByGroupId(
                    id = id,
                    startDate = startDate,
                    endDate = endDate
                ).onEach { result ->
                    when (result) {
                        is Resource.Success<List<ScheduleItemsForDay>> -> {
                            _state.value = _state.value.copy(
                                scheduleItemsForWeek = result.data ?: emptyList(),
                            )
                            Log.i("NAME", _state.value.name)
                        }
                        is Resource.Error<List<ScheduleItemsForDay>> -> {
                            _state.value = _state.value.copy(
                                errorMessage = result.message ?: "An unexpected error occured"
                            )
                            Log.i("errorMessage", _state.value.errorMessage)
                        }
                        is Resource.Loading<List<ScheduleItemsForDay>> -> {
                            _state.value = _state.value.copy(
                                isLoading = true
                            )
                            Log.i("loading", _state.value.isLoading.toString())
                        }
                    }
                }.launchIn(viewModelScope)
            }
            Constants.TEACHER -> {
                useCases.getScheduleForWeekByTeacherId(
                    id = id,
                    startDate = startDate,
                    endDate = endDate
                ).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                scheduleItemsForWeek = result.data ?: emptyList(),
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
                }.launchIn(viewModelScope)
            }
            Constants.CLASSROOM -> {
                useCases.getScheduleForWeekByStudyRoomId(
                    id = id,
                    startDate = startDate,
                    endDate = endDate
                ).onEach { result ->
                    when (result) {
                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                scheduleItemsForWeek = result.data ?: emptyList(),
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
                }.launchIn(viewModelScope)
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
                getScheduleForWeek(
                    id = _state.value.id,
                    type = _state.value.type,
                    name = _state.value.name
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
                getScheduleForWeek(
                    id = _state.value.id,
                    type = _state.value.type,
                    name = _state.value.name
                )
                Log.i("MONDAY OF PREVIOUS WEEK", _state.value.currentDay.toString())
            }
        }
    }
}