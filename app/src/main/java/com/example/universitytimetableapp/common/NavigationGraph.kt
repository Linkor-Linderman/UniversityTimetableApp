package com.example.universitytimetableapp.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.universitytimetableapp.feature_application_login.presentation.choice.ChoosingScreen
import com.example.universitytimetableapp.feature_application_login.presentation.first.FirstScreen
import com.example.universitytimetableapp.feature_application_login.presentation.login.LoginScreen
import com.example.universitytimetableapp.feature_application_login.presentation.registration.RegistrationScreen
import com.example.universitytimetableapp.feature_profile.presentation.profile.ProfileScreen
import com.example.universitytimetableapp.feature_schedule.presentation.detail_class_information_screen.DetailClassInformationScreen
import com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.ScheduleScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String = Screen.FirstScreen.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = Screen.FirstScreen.route
        ) {
            FirstScreen(navController)
        }
        composable(
            route = Screen.ChoosingScreen.route
        ) {
            ChoosingScreen(navController)
        }
        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(navController)
        }
        composable(
            route = Screen.RegistrationScreen.route
        ) {
            RegistrationScreen(navController)
        }
        composable(
            route = Screen.ProfileScreen.route
        ) {
            ProfileScreen(navController)
        }
        composable(
            route = Screen.ScheduleScreen.route
        ) {
            ScheduleScreen(navController)
        }
        composable(
            route = Screen.DetailClassInformationScreen.route + "/{className}/{classTime}/{classNumber}/{teacherName}/{classType}/{location}/{groupNumber}",
            arguments = listOf(
                navArgument("className") { NavType.StringType },
                navArgument("classTime") { NavType.StringType },
                navArgument("classNumber") { NavType.IntType },
                navArgument("teacherName") { NavType.StringType },
                navArgument("classType") { NavType.StringType },
                navArgument("location") { NavType.StringType },
                navArgument("groupNumber") { NavType.IntType },
            )
        ) {
            DetailClassInformationScreen(navController)
        }
    }
}