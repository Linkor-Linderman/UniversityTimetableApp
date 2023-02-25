package com.example.universitytimetableapp.feature_schedule.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.feature_schedule.presentation.composable.RowWithTextAndIcon
import com.example.universitytimetableapp.feature_schedule.presentation.composable.TopBarForDetailClassInformationScreen
import com.example.universitytimetableapp.ui.theme.brown
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview
@Composable
fun DetailClassInformationScreen() {
    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(
        color = brown
    )
    Scaffold(
        topBar = {
            TopBarForDetailClassInformationScreen()
        }
    ) {
        it
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 25.dp, end = 25.dp, top = 44.dp)
        ) {
            RowWithTextAndIcon(
                painter = painterResource(id = R.drawable.clock_icon),
                iconSize = 27.dp,
                text = "Something",
                textSize = 18.sp,
                spaceBetweenIconAndText = 15.dp
            )
            Spacer(modifier = Modifier.height(30.dp))
            RowWithTextAndIcon(
                painter = painterResource(id = R.drawable.teacher_icon),
                text = "Something",
                iconSize = 27.dp,
                textSize = 18.sp,
                spaceBetweenIconAndText = 15.dp
            )
            Spacer(modifier = Modifier.height(30.dp))
            RowWithTextAndIcon(
                painter = painterResource(id = R.drawable.class_type_icon),
                text = "Something",
                iconSize = 27.dp,
                textSize = 18.sp,
                spaceBetweenIconAndText = 15.dp
            )
            Spacer(modifier = Modifier.height(30.dp))
            RowWithTextAndIcon(
                painter = painterResource(id = R.drawable.place_of_the_lesson_icon),
                text = "Something",
                iconSize = 27.dp,
                textSize = 18.sp,
                spaceBetweenIconAndText = 15.dp
            )
            Spacer(modifier = Modifier.height(30.dp))
            RowWithTextAndIcon(
                painter = painterResource(id = R.drawable.group_icon),
                text = "Something",
                iconSize = 27.dp,
                textSize = 18.sp,
                spaceBetweenIconAndText = 15.dp
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}