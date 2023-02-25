package com.example.universitytimetableapp.feature_schedule.presentation.composable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universitytimetableapp.ui.theme.Zekton
import com.example.universitytimetableapp.ui.theme.brown

@Composable
fun TopBarForScheduleScreen(
    text: String?
) {
    TopAppBar(
        backgroundColor = brown,
        elevation = 0.dp,
        contentPadding = PaddingValues(start = 15.dp, top = 10.dp)
    ) {
        Text(
            text = text ?: "",
            color = Color.White,
            fontFamily = Zekton,
            fontSize = 20.sp
        )
    }
}