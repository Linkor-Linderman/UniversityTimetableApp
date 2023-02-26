package com.example.universitytimetableapp.feature_schedule.presentation


import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.universitytimetableapp.common.Screen
import com.example.universitytimetableapp.feature_schedule.presentation.composable.FloatingActionButtonForScheduleScreen
import com.example.universitytimetableapp.feature_schedule.presentation.composable.TimeTableForWeek
import com.example.universitytimetableapp.feature_schedule.presentation.composable.TopBarForScheduleScreen
import com.example.universitytimetableapp.ui.theme.brown
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ScheduleScreen(
    navController: NavController
) {

    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(
        color = brown
    )

    Scaffold(
        topBar = { TopBarForScheduleScreen(text = "972101") },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButtonForScheduleScreen { navController.navigate(Screen.ProfileScreen.route) } }
    ) { padding ->
        TimeTableForWeek(
            modifier = Modifier.padding(padding)
        )
    }
}
