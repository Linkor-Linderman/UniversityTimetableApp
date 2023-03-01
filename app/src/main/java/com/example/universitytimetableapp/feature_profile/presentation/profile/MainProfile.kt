package com.example.universitytimetableapp.feature_profile.presentation.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.common.Constants
import com.example.universitytimetableapp.common.Screen
import com.example.universitytimetableapp.feature_profile.presentation.ChangePasswordField
import com.example.universitytimetableapp.feature_profile.presentation.ProfileButton
import com.example.universitytimetableapp.feature_profile.presentation.ProfileLeftText
import com.example.universitytimetableapp.feature_profile.presentation.ProfileRightText
import com.example.universitytimetableapp.ui.theme.Zekton
import com.example.universitytimetableapp.ui.theme.brown

@Composable
fun MainProfile(padding: PaddingValues, navController: NavController) {
    val isStudent = true
    var showDialog by remember { mutableStateOf(false) }

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
                ProfileRightText(name = "Феофилов")
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
                ProfileRightText(name = "Алексей")
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
                ProfileRightText(name = "Дмитриевич")
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
                ProfileRightText(name = "fil_master@gmail.com")
            }
            if (isStudent) {
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
                    ProfileRightText(name = "972101")
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .weight(1f)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = if (isStudent) Arrangement.SpaceAround else Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            if (isStudent) {
                ProfileButton(name = stringResource(R.string.change_group),
                    click = {navController.navigate("${Screen.ChoosingScreen.route}/${Constants.CHANGE_GROUP}?${Constants.EMAIL}=test@mail.ru")}
                )
            }
            ProfileButton(name = stringResource(R.string.change_password), click = { showDialog = true })
        }
    }

    if (showDialog) {
        val oldPassword = remember { mutableStateOf("") }
        val newPassword = remember { mutableStateOf("") }
        val isInputCorrect = remember { mutableStateOf(false) }

        isInputCorrect.value = oldPassword.value.isNotEmpty() && newPassword.value.isNotEmpty()

        Dialog(onDismissRequest = { showDialog = false }) {
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
                    ChangePasswordField(state = oldPassword, valChange = {s -> oldPassword.value = s })
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
                    ChangePasswordField(state = newPassword, valChange = {s -> newPassword.value = s })
                }
                Button(
                    onClick = {
                        showDialog = false
                    },
                    modifier = Modifier
                        .padding(top = 40.dp)
                        .size(180.dp, 35.dp),
                    border = if (isInputCorrect.value) {
                        null
                    } else {
                        BorderStroke(1.dp, brown)
                    },
                    colors = ButtonDefaults.buttonColors(
                        disabledBackgroundColor = Color.White,
                        backgroundColor = brown
                    ),
                    shape = RoundedCornerShape(15.dp),
                    enabled = isInputCorrect.value
                ) {
                    Text(
                        text = stringResource(R.string.changing),
                        fontFamily = Zekton,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        color = if (isInputCorrect.value) {
                            Color.White
                        } else {
                            Color.Black
                        }
                    )
                }
            }
        }
    }
}