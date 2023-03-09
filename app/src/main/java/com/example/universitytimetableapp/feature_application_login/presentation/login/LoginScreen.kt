package com.example.universitytimetableapp.feature_application_login.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.observeAsState()
    if (state!!.mayNavigate) {
        viewModel.setDefaultState()
        navController.navigate(state!!.destinationString) {
            popUpTo(Screen.FirstScreen.route) {
                inclusive = true
            }
        }
    }

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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.1f))
        Image(
            painter = ColorPainter(greyTint),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(0.35f)
                .fillMaxHeight(0.2f)
                .align(Alignment.CenterHorizontally)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
        ) {
            InputField(state = viewModel.login.observeAsState(""), valChange = { viewModel.setLogin(it) }, name = stringResource(R.string.login))
            Spacer(modifier = Modifier.padding(8.dp))
            InputField(state = viewModel.password.observeAsState(""), valChange = { viewModel.setPassword(it) }, name = stringResource(R.string.password), isPassword = true)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = 25.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FirstButton(name = stringResource(R.string.enter), state = viewModel.isCorrectData.observeAsState(false),
                click = { viewModel.login() }
            )
            Spacer(modifier = Modifier.padding(5.dp))
            SecondButton(name = stringResource(R.string.have_not_account),
                click = { navController.navigate("${Screen.ChoosingScreen.route}/${Constants.REGISTER}") }
            )
        }
    }
}