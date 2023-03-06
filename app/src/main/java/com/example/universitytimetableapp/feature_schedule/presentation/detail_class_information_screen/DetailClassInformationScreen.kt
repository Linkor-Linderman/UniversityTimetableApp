package com.example.universitytimetableapp.feature_schedule.presentation.detail_class_information_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.feature_schedule.presentation.common_composable.RowWithTextAndIcon
import com.example.universitytimetableapp.feature_schedule.presentation.detail_class_information_screen.composable.TopBarForDetailClassInformationScreen
import com.example.universitytimetableapp.ui.theme.brown
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun DetailClassInformationScreen(
    navController: NavController,
    viewModel: DetailClassInformationViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(
        color = brown
    )

    val state = viewModel.state.value
    val coupleWord = stringResource(id = R.string.couple)

    Scaffold(
        topBar = {
            TopBarForDetailClassInformationScreen(
                className = state.className,
                onClick = {
                    navController.popBackStack()
                }
            )
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
                text = "${state.classTime} ❢ ${state.classNumber} $coupleWord",
                textSize = 18.sp,
                spaceBetweenIconAndText = 15.dp
            )
            Spacer(modifier = Modifier.height(30.dp))
            RowWithTextAndIcon(
                painter = painterResource(id = R.drawable.teacher_icon),
                text = state.teacherName,
                iconSize = 27.dp,
                textSize = 18.sp,
                spaceBetweenIconAndText = 15.dp
            )
            Spacer(modifier = Modifier.height(30.dp))
            RowWithTextAndIcon(
                painter = painterResource(id = R.drawable.class_type_icon),
                text = state.classType,
                iconSize = 27.dp,
                textSize = 18.sp,
                spaceBetweenIconAndText = 15.dp
            )
            Spacer(modifier = Modifier.height(30.dp))
            RowWithTextAndIcon(
                painter = painterResource(id = R.drawable.place_of_the_lesson_icon),
                text = state.location,
                iconSize = 27.dp,
                textSize = 18.sp,
                spaceBetweenIconAndText = 15.dp
            )
            Spacer(modifier = Modifier.height(30.dp))
            RowWithTextAndIcon(
                painter = painterResource(id = R.drawable.group_icon),
                text = state.groupNumber.toString(),
                iconSize = 27.dp,
                textSize = 18.sp,
                spaceBetweenIconAndText = 15.dp
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}