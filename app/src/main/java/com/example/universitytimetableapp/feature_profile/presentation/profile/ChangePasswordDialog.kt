package com.example.universitytimetableapp.feature_profile.presentation.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.feature_profile.presentation.ChangePasswordField
import com.example.universitytimetableapp.feature_profile.presentation.ProfileLeftText
import com.example.universitytimetableapp.ui.theme.Zekton
import com.example.universitytimetableapp.ui.theme.brown

@Composable
fun ChangePasswordDialog(viewModel: ProfileViewModel) {
    val isInputCorrect by viewModel.isCorrectData.observeAsState(false)

    Dialog(onDismissRequest = { viewModel.showOrCloseDialog() }) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .padding(10.dp, 15.dp, 10.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.changing_password),
                fontFamily = Zekton,
                fontWeight = FontWeight.Normal,
                fontSize = 30.sp
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 40.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.fillMaxWidth(0.45f)) {
                    ProfileLeftText(name = stringResource(R.string.old_password))
                }
                ChangePasswordField(state = viewModel.oldPassword.observeAsState(""), valChange = { viewModel.setOldPassword(it) })
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.fillMaxWidth(0.45f)) {
                    ProfileLeftText(name = stringResource(R.string.new_password))
                }
                ChangePasswordField(state = viewModel.newPassword.observeAsState(""), valChange = { viewModel.setNewPassword(it) })
            }
            Button(
                onClick = {
                    viewModel.changePassword()
                },
                modifier = Modifier
                    .padding(top = 40.dp)
                    .size(180.dp, 35.dp),
                border = if (isInputCorrect) {
                    null
                } else {
                    BorderStroke(1.dp, brown)
                },
                colors = ButtonDefaults.buttonColors(
                    disabledBackgroundColor = Color.White,
                    backgroundColor = brown
                ),
                shape = RoundedCornerShape(15.dp),
                enabled = isInputCorrect
            ) {
                Text(
                    text = stringResource(R.string.changing),
                    fontFamily = Zekton,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = if (isInputCorrect) {
                        Color.White
                    } else {
                        Color.Black
                    }
                )
            }
        }
    }
}