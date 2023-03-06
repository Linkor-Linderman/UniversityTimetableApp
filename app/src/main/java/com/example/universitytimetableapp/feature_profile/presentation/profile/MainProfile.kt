package com.example.universitytimetableapp.feature_profile.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.common.Constants
import com.example.universitytimetableapp.feature_profile.presentation.ProfileButton
import com.example.universitytimetableapp.feature_profile.presentation.ProfileLeftText
import com.example.universitytimetableapp.feature_profile.presentation.ProfileRightText

@Composable
fun MainProfile(
    padding: PaddingValues,
    viewModel: ProfileViewModel
) {
    val uiState by viewModel.uiState.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .padding(padding)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(start = 25.dp, top = 48.dp, end = 25.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.fillMaxWidth(0.25f)) {
                    ProfileLeftText(name = stringResource(R.string.surname))
                }
                ProfileRightText(name = uiState!!.profile?.surname ?: "")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.fillMaxWidth(0.25f)) {
                    ProfileLeftText(name = stringResource(R.string.name))
                }
                ProfileRightText(name = uiState!!.profile?.name ?: "")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.fillMaxWidth(0.25f)) {
                    ProfileLeftText(name = stringResource(R.string.patronymic))
                }
                ProfileRightText(name = uiState!!.profile?.patronymic ?: "")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.fillMaxWidth(0.25f)) {
                    ProfileLeftText(name = stringResource(R.string.email))
                }
                ProfileRightText(name = uiState!!.profile?.email ?: "")
            }
            if (uiState!!.isStudent) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.fillMaxWidth(0.25f)) {
                        ProfileLeftText(name = stringResource(R.string.group))
                    }
                    ProfileRightText(name = uiState!!.profile?.groupNumber ?: "")
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .weight(1f)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = if (uiState!!.isStudent) Arrangement.SpaceAround else Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            if (uiState!!.isStudent) {
                ProfileButton(name = stringResource(R.string.change_group),
                    click = { viewModel.goToNextScreen(Constants.CHANGE_GROUP) }
                )
            }
            ProfileButton(name = stringResource(R.string.change_password), click = { viewModel.showOrCloseDialog() })
        }
    }

    if (uiState!!.isShowDialog) {
        ChangePasswordDialog(viewModel)
    }
}