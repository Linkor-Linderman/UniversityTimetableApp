package com.example.universitytimetableapp.feature_profile.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.feature_profile.presentation.FillProfileButton
import com.example.universitytimetableapp.feature_profile.presentation.ProfileButton
import com.example.universitytimetableapp.ui.theme.Jura

@Composable
fun GuestProfile(padding: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.3f))
        Text(
            text = stringResource(R.string.not_enter_in_account),
            fontFamily = Jura,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.fillMaxHeight(0.2f))
        FillProfileButton(name = stringResource(R.string.entrance), click = {})
        Spacer(modifier = Modifier.padding(10.dp))
        FillProfileButton(name = stringResource(R.string.registration), click = {})

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(start = 40.dp, bottom = 15.dp, end = 40.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            ProfileButton(name = stringResource(R.string.change_initial_choice), click = {})
        }
    }
}