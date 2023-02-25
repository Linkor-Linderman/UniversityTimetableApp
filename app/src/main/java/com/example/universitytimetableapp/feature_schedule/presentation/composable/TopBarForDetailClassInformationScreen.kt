package com.example.universitytimetableapp.feature_schedule.presentation.composable


import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universitytimetableapp.ui.theme.Zekton
import com.example.universitytimetableapp.ui.theme.brown

@Preview
@Composable
fun TopBarForDetailClassInformationScreen(
    text: String = "Математический анализ"
) {
    TopAppBar(
        modifier = Modifier
            .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)),
        title = {
            Text(
                text = text ?: "",
                color = Color.White,
                fontFamily = Zekton,
                fontSize = 22.sp
            )
        },
        navigationIcon = {
            Icon(
                modifier = Modifier.clickable { },
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "navigation icon",
                tint = Color.White
            )
        },
        backgroundColor = brown
    )
}