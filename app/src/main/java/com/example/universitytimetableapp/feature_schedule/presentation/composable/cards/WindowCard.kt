package com.example.universitytimetableapp.feature_schedule.presentation.composable.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universitytimetableapp.ui.theme.Jura

@Preview
@Composable
fun WindowCard(
    timeInterval: String = "Something",
    numberOfClassInThisWindow: Int = 0
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 23.dp)
        ) {
            Text(
                text = timeInterval,
                fontFamily = Jura,
                color = Color.Black,
                fontWeight = FontWeight.Light,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier
                    .wrapContentHeight(Alignment.Bottom),
                text = "❢",
                fontFamily = Jura,
                color = Color.Black,
                fontSize = 15.sp,
            )

            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = numberOfClassInThisWindow.toString(),
                fontFamily = Jura,
                color = Color.Black,
                fontSize = 15.sp
            )
        }
    }
}