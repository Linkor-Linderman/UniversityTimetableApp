package com.example.universitytimetableapp.feature_schedule.presentation.common_composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.ui.theme.Jura

@Composable
fun RowWithTextAndIcon(
    painter: Painter = painterResource(id = R.drawable.group_icon),
    text: String = "",
    iconSize: Dp = 15.dp,
    textSize: TextUnit = 12.sp,
    spaceBetweenIconAndText: Dp = 7.dp
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(iconSize),
            painter = painter,
            tint = Color.Black,
            contentDescription = "icon"
        )
        Spacer(modifier = Modifier.width(spaceBetweenIconAndText))
        Text(
            text = text,
            fontSize = textSize,
            fontFamily = Jura,
            color = Color.Black
        )
    }
}