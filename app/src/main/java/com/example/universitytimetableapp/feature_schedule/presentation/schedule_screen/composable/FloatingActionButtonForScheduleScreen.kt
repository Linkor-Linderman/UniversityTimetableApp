package com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.composable

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.ui.theme.brown


@Composable
fun FloatingActionButtonForScheduleScreen(
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = brown
    ) {
        Icon(
            painter = painterResource(id = R.drawable.profile_icon),
            contentDescription = "fab icon",
            tint = Color.White
        )
    }
}