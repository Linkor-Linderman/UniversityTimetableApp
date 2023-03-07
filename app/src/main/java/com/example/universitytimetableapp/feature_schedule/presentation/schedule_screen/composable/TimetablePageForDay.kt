package com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.common.Screen
import com.example.universitytimetableapp.feature_schedule.domain.model.EndTime
import com.example.universitytimetableapp.feature_schedule.domain.model.ScheduleItem
import com.example.universitytimetableapp.feature_schedule.domain.model.ScheduleItemsForDay
import com.example.universitytimetableapp.feature_schedule.domain.model.StartTime
import com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.composable.cards.BreakCard
import com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.composable.cards.SubjectTimeslotCard
import com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.composable.cards.WindowCard
import com.example.universitytimetableapp.ui.theme.Jura
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TimetablePageForDay(
    modifier: Modifier = Modifier,
    navController: NavController,
    scheduleItemsForDay: ScheduleItemsForDay
) {
    val listOfScheduleItemCard = scheduleItemsForDay.listOfScheduleItemCard

    if (listOfScheduleItemCard.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.empty_day),
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = Jura,
                fontWeight = FontWeight.Bold
            )
        }
    }
    else{
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
                                        buildString {
                                            item.lesson.groups.forEach {
                                                append(it.number + ", ")
                                            }
                                        }.dropLast(2),
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
                Spacer(modifier = Modifier.height(20.dp))
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
    return (difference / 60000).toString()
}




