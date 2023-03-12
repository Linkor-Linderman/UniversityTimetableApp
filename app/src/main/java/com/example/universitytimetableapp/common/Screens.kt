package com.example.universitytimetableapp.common

sealed class Screen(val route: String) {
    object ChoosingScreen : Screen("choosing_screen")
    object FirstScreen : Screen("first_screen")
    object LoginScreen : Screen("login_screen")
    object RegistrationScreen : Screen("registration_screen")
    object GuestProfileScreen : Screen("guest_profile_screen")
    object MainProfileScreen : Screen("main_profile_screen")
    object ProfileScreen : Screen("profile_screen")
    object DetailClassInformationScreen : Screen("detail_class_information_screen")
    object ScheduleScreen : Screen("schedule_screen")

    fun withArg(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
