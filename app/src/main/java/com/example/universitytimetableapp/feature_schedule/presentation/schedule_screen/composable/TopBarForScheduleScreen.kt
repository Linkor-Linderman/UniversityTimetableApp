package com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.ui.theme.Zekton
import com.example.universitytimetableapp.ui.theme.brown
import com.example.universitytimetableapp.ui.theme.greyForSelectedDay

@Composable
fun TopBarForScheduleScreen(
    text: String?,
    month: Int,
    year: String,
) {
    val namesOfMonth = listOf(
        stringResource(id =  R.string.January),
        stringResource(id = R.string.February),
        stringResource(id = R.string.March),
        stringResource(id = R.string.April),
        stringResource(id = R.string.May),
        stringResource(id = R.string.June),
        stringResource(id = R.string.July),
        stringResource(id = R.string.August),
        stringResource(id = R.string.September),
        stringResource(id = R.string.October),
        stringResource(id = R.string.November),
        stringResource(id = R.string.December)
    )
    TopAppBar(
        backgroundColor = brown,
        elevation = 0.dp,
        contentPadding = PaddingValues(start = 15.dp, top = 10.dp)
    ) {
        Column() {
            Text(
                text = text ?: "",
                color = Color.White,
                fontFamily = Zekton,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "${namesOfMonth[month]} $year",
                color = greyForSelectedDay,
                fontFamily = Zekton,
                fontSize = 14.sp
            )
        }
    }
}