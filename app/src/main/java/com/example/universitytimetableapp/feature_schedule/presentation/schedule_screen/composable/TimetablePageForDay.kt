package com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.common.Screen
import com.example.universitytimetableapp.feature_schedule.domain.model.*
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
    val onlineWord = stringResource(id = R.string.online)

    if (listOfScheduleItemCard.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.rest_area_icon_com),
                    contentDescription = "Rest area icon",
                    modifier = Modifier.size(50.dp),
                    tint = Color.LightGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = stringResource(id = R.string.empty_day),
                    color = Color.LightGray,
                    fontSize = 20.sp,
                    fontFamily = Jura,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    } else {
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
                                        item.lesson.teacher.getFullName(),
                                        item.lesson.lessonType.name,
                                        if (item.lesson.studyRoom == null)
                                            onlineWord
                                        else
                                            item.lesson.studyRoom.getStudyRoomLocal(),
                                        item.lesson.getGroupsString(),
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
    startTime: LocalTimeModel,
    endTime: LocalTimeModel
): String {
    val format = SimpleDateFormat("HH:mm")
    val date1: Date = format.parse("${startTime.hour}:${startTime.minute}")
    val date2: Date = format.parse("${endTime.hour}:${endTime.minute}")
    val difference: Long = date2.time - date1.time
    return (difference / 60000).toString()
}




