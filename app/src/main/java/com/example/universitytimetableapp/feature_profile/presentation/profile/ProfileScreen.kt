package com.example.universitytimetableapp.feature_profile.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.ui.theme.Jura
import com.example.universitytimetableapp.ui.theme.Zekton
import com.example.universitytimetableapp.ui.theme.brown

@Composable
fun ProfileScreen(
    navController: NavController
) {
    val isGuest = false

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.clip(
                    RoundedCornerShape(
                        bottomStart = 20.dp,
                        bottomEnd = 20.dp
                    )
                ),
                backgroundColor = brown
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.back_arrow),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    Text(
                        text = stringResource(R.string.profile),
                        fontFamily = Zekton,
                        fontWeight = FontWeight.Normal,
                        fontSize = 22.sp,
                        color = Color.White
                    )
                    TextButton(
                        onClick = { },
                        modifier = Modifier.alpha(if (isGuest) 0f else 1f),
                        enabled = !isGuest
                    ) {
                        Text(
                            text = stringResource(R.string.exit),
                            fontFamily = Zekton,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .height(90.dp),
                backgroundColor = brown
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = stringResource(R.string.watch_another_schedule),
                        fontFamily = Jura,
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.fillMaxWidth(0.25f))
                    IconButton(onClick = { }) {
                        Icon(imageVector = ImageVector.vectorResource(R.drawable.choice_arrow),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }
    ) { padding ->
        if (isGuest) {
            GuestProfile(padding = padding)
        }
        else {
            MainProfile(padding = padding)
        }
    }
}