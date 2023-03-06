package com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.composable

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.universitytimetableapp.common.Screen
import com.example.universitytimetableapp.feature_schedule.domain.model.EndTime
import com.example.universitytimetableapp.feature_schedule.domain.model.ScheduleItem
import com.example.universitytimetableapp.feature_schedule.domain.model.ScheduleItemsForDay
import com.example.universitytimetableapp.feature_schedule.domain.model.StartTime
import com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.composable.cards.BreakCard
import com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.composable.cards.SubjectTimeslotCard
import com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.composable.cards.WindowCard
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TimetablePageForDay(
    modifier: Modifier = Modifier,
    navController: NavController,
    scheduleItemsForDay: ScheduleItemsForDay
) {
    val listOfScheduleItemCard = scheduleItemsForDay.listOfScheduleItemCard
    LazyColumn(
        modifier = modifier
    ) {
        items(listOfScheduleItemCard) { item: ScheduleItem ->
            when (item) {
                is ScheduleItem.BreakItem -> {
                    BreakCard(
                        text = getPeriodInMinutes(item.startTime, item.endTime)
                    )
                }
                is ScheduleItem.SubjectItem -> {
                    SubjectTimeslotCard(
                        onClick = {
                            navController.navigate(
                                Screen.DetailClassInformationScreen.withArg(
                                    item.lesson.subject.name,
                                    item.lesson.lessonTime.getTimePeriod(),
                                    item.lesson.lessonTime.lessonNumber.toString(),
                                    "${item.lesson.teacher.lastName} ${item.lesson.teacher.firstName} ${item.lesson.teacher.patronymicName}",
                                    item.lesson.lessonType.name,
                                    "${item.lesson.studyRoom.buildingNumber}  ${item.lesson.studyRoom.number} (${item.lesson.studyRoom.floor}) ${item.lesson.studyRoom.name}",
                                    item.lesson.groups.toString(),
                                )
                            )
                        },
                        lesson = item.lesson
                    )
                }
                is ScheduleItem.WindowItem -> {
                    WindowCard(
                        timeInterval = "${item.startTime.getHourAndMinute()} - ${item.endTime.getHourAndMinute()}",
                        numberOfClassInThisWindow = item.windowNumber
                    )
                }
            }
        }
    }
}

fun getPeriodInMinutes(
    startTime: EndTime,
    endTime: StartTime
): String {
    val format = SimpleDateFormat("HH:mm")
    val date1: Date = format.parse("${startTime.hour}:${startTime.minute}")
    val date2: Date = format.parse("${endTime.hour}:${endTime.minute}")
    val difference: Long = date1.time - date2.time
    return difference.toString()
}