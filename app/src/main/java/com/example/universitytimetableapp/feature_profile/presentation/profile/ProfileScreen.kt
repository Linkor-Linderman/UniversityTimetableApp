package com.example.universitytimetableapp.feature_profile.presentation.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.common.Constants
import com.example.universitytimetableapp.common.Screen
import com.example.universitytimetableapp.feature_profile.presentation.AppProgressIndicator
import com.example.universitytimetableapp.ui.theme.Jura
import com.example.universitytimetableapp.ui.theme.Zekton
import com.example.universitytimetableapp.ui.theme.brown

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.observeAsState()

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
                    IconButton(onClick = { navController.navigateUp() }) {
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
                        onClick = { viewModel.goToNextScreen(Constants.EXIT) },
                        modifier = Modifier.alpha(if (state!!.isGuest) 0f else 1f),
                        enabled = !state!!.isGuest
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.goToNextScreen(Constants.CHOOSE_SCHEDULE)
                        },
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = stringResource(R.string.watch_another_schedule),
                        fontFamily = Jura,
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.fillMaxWidth(0.35f))
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.choice_arrow),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier
                            .padding(end = 25.dp)
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    ) { padding ->
        if (state!!.mayNavigate) {
            viewModel.setDefaultState()
            navController.navigate(state!!.destinationString) {
                if (state!!.isExit) {
                    popUpTo(Screen.ProfileScreen.route) { inclusive = true }
                }
            }
        }
        if (state!!.isLoading) {
            AppProgressIndicator(brown)
        }
        else if (state!!.isGuest) {
            GuestProfile(padding = padding, viewModel)
        }
        else {
            MainProfile(padding = padding, viewModel)
        }
    }
}