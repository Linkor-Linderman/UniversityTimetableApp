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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.feature_schedule.presentation.common_composable.RowWithTextAndIcon
import com.example.universitytimetableapp.ui.theme.Jura

@Composable
fun SubjectTimeslotCard(
    onClick: () -> Unit
) {
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
                text = "Математический анализ",
                fontSize = 18.sp,
                color = Color.Black,
                fontFamily = Jura,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(11.dp))
            RowWithTextAndIcon(
                text = "Лекция",
                painter = painterResource(
                    id = R.drawable.class_type_icon,
                )
            )
            Spacer(modifier = Modifier.height(7.dp))
            RowWithTextAndIcon(
                text = "Диана Даммировна",
                painter = painterResource(
                    id = R.drawable.teacher_icon,
                )
            )
            Spacer(modifier = Modifier.height(7.dp))
            RowWithTextAndIcon(
                text = "332 (2) Учебная аудитория",
                painter = painterResource(
                    id = R.drawable.place_of_the_lesson_icon,
                )
            )
            Spacer(modifier = Modifier.height(7.dp))
            RowWithTextAndIcon(
                text = "972101",
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
                    text = "4 пара",
                    fontSize = 10.sp,
                    fontFamily = Jura,
                    color = Color.Black
                )
                Text(
                    text = "14:45 - 16:20",
                    fontSize = 15.sp,
                    fontFamily = Jura,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}
