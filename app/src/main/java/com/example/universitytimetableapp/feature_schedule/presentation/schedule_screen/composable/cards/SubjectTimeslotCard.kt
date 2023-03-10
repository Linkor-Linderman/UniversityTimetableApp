package com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.composable.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.feature_schedule.domain.model.Lesson
import com.example.universitytimetableapp.feature_schedule.presentation.common_composable.RowWithTextAndIcon
import com.example.universitytimetableapp.ui.theme.Jura

@Composable
fun SubjectTimeslotCard(
    onClick: () -> Unit,
    lesson: Lesson
) {
    val coupleWord = stringResource(id = R.string.couple)
    val onlineWord = stringResource(id = R.string.online)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        backgroundColor = Color.LightGray,
        elevation = 0.dp,
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 12.dp)
        ) {
            Text(
                text = lesson.subject.name,
                fontSize = 18.sp,
                color = Color.Black,
                fontFamily = Jura,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(11.dp))
            RowWithTextAndIcon(
                text = lesson.lessonType.name,
                painter = painterResource(
                    id = R.drawable.class_type_icon,
                )
            )
            Spacer(modifier = Modifier.height(7.dp))
            RowWithTextAndIcon(
                text = lesson.teacher.getFullName(),
                painter = painterResource(
                    id = R.drawable.teacher_icon,
                )
            )
            Spacer(modifier = Modifier.height(7.dp))
            RowWithTextAndIcon(
                text = if (lesson.studyRoom == null) {
                    onlineWord
                } else {
                    lesson.studyRoom.getStudyRoomLocal()
                },
                painter = painterResource(
                    id = R.drawable.place_of_the_lesson_icon,
                )
            )
            Spacer(modifier = Modifier.height(7.dp))
            RowWithTextAndIcon(
                text = lesson.getGroupsString(),
                painter = painterResource(
                    id = R.drawable.group_icon,
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "${lesson.lessonTime.lessonNumber} $coupleWord",
                    fontSize = 10.sp,
                    fontFamily = Jura,
                    color = Color.Black
                )
                Text(
                    text = lesson.lessonTime.getTimePeriod(),
                    fontSize = 15.sp,
                    fontFamily = Jura,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}
