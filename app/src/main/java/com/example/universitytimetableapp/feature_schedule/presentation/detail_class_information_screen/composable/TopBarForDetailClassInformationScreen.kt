package com.example.universitytimetableapp.feature_schedule.presentation.detail_class_information_screen.composable

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.ui.theme.Zekton
import com.example.universitytimetableapp.ui.theme.brown

@Composable
fun TopBarForDetailClassInformationScreen(
    className: String = "",
    onClick: () -> Unit
) {
    TopAppBar(
        modifier = Modifier
            .height(100.dp)
            .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)),
        title = {
            Text(
                text = className ?: "",
                color = Color.White,
                fontFamily = Zekton,
                fontSize = 22.sp
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    onClick()
                }
            ) {
                Icon(
                    modifier = Modifier,
                    imageVector =  ImageVector.vectorResource(R.drawable.back_arrow),
                    contentDescription = "navigation icon",
                    tint = Color.White
                )
            }
        },
        backgroundColor = brown
    )
}