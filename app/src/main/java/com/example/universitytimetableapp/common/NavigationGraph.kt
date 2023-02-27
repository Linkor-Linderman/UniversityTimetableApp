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
import com.example.universitytimetableapp.feature_schedule.presentation.DetailClassInformationScreen
import com.example.universitytimetableapp.feature_schedule.presentation.ScheduleScreen

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
            route = "${Screen.ChoosingScreen.route}/{${Constants.CASE}}",
            arguments = listOf(
                navArgument(Constants.CASE) { type = NavType.StringType }
            )
        ) {
            ChoosingScreen(navController)
        }
        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(navController)
        }
        composable(
            route = "${Screen.RegistrationScreen.route}/{${Constants.TYPE_USER}}/{${Constants.ID}}?${Constants.TEACHER_NAME}={${Constants.TEACHER_NAME}}",
            arguments = listOf(
                navArgument(Constants.TYPE_USER) { type = NavType.StringType },
                navArgument(Constants.ID) { type = NavType.StringType },
                navArgument(Constants.TEACHER_NAME) { defaultValue = "" }
            )
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
            route = Screen.DetailClassInformationScreen.route
        ) {
            DetailClassInformationScreen(navController)
        }
    }
}