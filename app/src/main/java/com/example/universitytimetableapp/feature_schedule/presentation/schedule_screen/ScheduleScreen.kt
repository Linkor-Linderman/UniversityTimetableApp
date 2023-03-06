package com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen


import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.universitytimetableapp.common.Screen
import com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.composable.TimeTableForWeek
import com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.composable.TopBarForScheduleScreen
import com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.composable.FloatingActionButtonForScheduleScreen
import com.example.universitytimetableapp.ui.theme.brown
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.example.universitytimetableapp.R

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ScheduleScreen(
    navController: NavController,
    viewModel: ScheduleScreenViewModel = hiltViewModel(),
) {
    val systemUiController = rememberSystemUiController()
    val state = viewModel.state.value
    val groupWord = stringResource(id = R.string.group)

    systemUiController.setSystemBarsColor(
        color = brown
    )

    Scaffold(
        topBar = {
            TopBarForScheduleScreen(
                text = if (state.type =="STUDENT") "$groupWord ${state.name}" else state.name,
                month = state.monthOfCurrentWeek,
                year = state.yearOfCurrentWeek
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButtonForScheduleScreen {
                navController.navigate(
                    Screen.ProfileScreen.route
                )
            }
        }
    ) { padding ->
        TimeTableForWeek(
            modifier = Modifier.padding(padding),
            state = state,
            onEvent = {
                viewModel.onEvent(it)
            },
            navController = navController
        )
    }
}
