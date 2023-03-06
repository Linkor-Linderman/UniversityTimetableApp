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
            route = "${Screen.ChoosingScreen.route}/{${Constants.CASE}}?${Constants.EMAIL}={${Constants.EMAIL}}",
            arguments = listOf(
                navArgument(Constants.CASE) { type = NavType.StringType },
                navArgument(Constants.EMAIL) { defaultValue = "" }
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
            route = "${Screen.RegistrationScreen.route}/{${Constants.TYPE_USER}}/{${Constants.ID}}/{${Constants.TEACHER_GROUP_NAME}}",
            arguments = listOf(
                navArgument(Constants.TYPE_USER) { type = NavType.StringType },
                navArgument(Constants.ID) { type = NavType.StringType },
                navArgument(Constants.TEACHER_GROUP_NAME) { type = NavType.StringType }
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
            route = "${Screen.ScheduleScreen.route}/{${Constants.TYPE_USER}}/{${Constants.ID}}/{${Constants.TEACHER_GROUP_NAME}}",
            arguments = listOf(
                navArgument(Constants.TYPE_USER) { type = NavType.StringType },
                navArgument(Constants.ID) { type = NavType.StringType },
                navArgument(Constants.TEACHER_GROUP_NAME) { type = NavType.StringType }
            )
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