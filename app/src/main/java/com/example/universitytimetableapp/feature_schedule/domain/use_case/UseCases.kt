package com.example.universitytimetableapp.feature_schedule.domain.use_case

data class UseCases(
    val getDayNumbersOfAWeek: GetDateTimesForCurrentWeekUseCase,
    val getNextMondayUseCase: GetNextMondayUseCase,
    val getPreviousMondayUseCase: GetPreviousMondayUseCase,
    val getScheduleForWeekByGroupId: GetScheduleForWeekByGroupId,
    val getScheduleForWeekByTeacherId: GetScheduleForWeekByTeacherId
)
