package com.example.universitytimetableapp.feature_application_login.presentation.registration

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.common.Constants
import com.example.universitytimetableapp.common.Screen
import com.example.universitytimetableapp.feature_application_login.presentation.FirstButton
import com.example.universitytimetableapp.feature_application_login.presentation.InputField
import com.example.universitytimetableapp.feature_application_login.presentation.SecondButton
import com.example.universitytimetableapp.ui.theme.greyTint

@Composable
fun RegistrationScreen(
    navController: NavController,
    viewModel: RegistrationViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.brown_background_form),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.85f)
                .padding(top = 10.dp),
            contentScale = ContentScale.FillBounds
        )
    }
    Column {
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Image(
            painter = ColorPainter(greyTint),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.2f)
                .fillMaxHeight(0.12f)
                .align(Alignment.CenterHorizontally)
        )
        Column(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()
                .fillMaxHeight(0.68f)
                .verticalScroll(rememberScrollState())
        ) {
            InputField(
                state = viewModel.surname.observeAsState(),
                valChange = { viewModel.setSurname(it) }, name = stringResource(R.string.surname),
                isEnable = viewModel.role != Constants.TEACHER
            )
            Spacer(modifier = Modifier.padding(8.dp))
            InputField(
                state = viewModel.name.observeAsState(),
                valChange = { viewModel.setName(it) }, name = stringResource(R.string.name),
                isEnable = viewModel.role != Constants.TEACHER
            )
            Spacer(modifier = Modifier.padding(8.dp))
            InputField(
                state = viewModel.patronymic.observeAsState(),
                valChange = { viewModel.setPatronymic(it) }, name = stringResource(R.string.patronymic),
                isEnable = viewModel.role != Constants.TEACHER
            )
            Spacer(modifier = Modifier.padding(8.dp))
            InputField(
                state = viewModel.email.observeAsState(),
                valChange = { viewModel.setEmail(it) }, name = stringResource(R.string.email)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            InputField(
                state = viewModel.password.observeAsState(),
                valChange = { viewModel.setPassword(it) }, name = stringResource(R.string.password), isPassword = true
            )
            Spacer(modifier = Modifier.padding(8.dp))
            InputField(
                state = viewModel.confirmPassword.observeAsState(),
                valChange = { viewModel.setConfirmPassword(it) }, name = stringResource(R.string.confirm_password),
                isPassword = true
            )
        }
        Log.e("recompose", "========")
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = 25.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FirstButton(
                name = stringResource(R.string.register),
                state = viewModel.isCorrectData.observeAsState(false),
                click = { navController.navigate(Screen.ScheduleScreen.route) }
            )
            Spacer(modifier = Modifier.padding(5.dp))
            SecondButton(
                name = stringResource(R.string.have_account),
                click = { navController.navigate(Screen.LoginScreen.route) }
            )
        }
    }
}