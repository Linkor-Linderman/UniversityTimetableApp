package com.example.universitytimetableapp.feature_application_login.presentation.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.common.Screen
import com.example.universitytimetableapp.feature_application_login.presentation.FirstButton
import com.example.universitytimetableapp.feature_application_login.presentation.InputField
import com.example.universitytimetableapp.feature_application_login.presentation.SecondButton
import com.example.universitytimetableapp.ui.theme.greyTint

@Composable
fun RegistrationScreen(
    navController: NavController
) {
    val surname = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val patronymic = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val inputNotEmpty = remember { mutableStateOf(false) }

    inputNotEmpty.value = email.value.isNotEmpty() && password.value.isNotEmpty()

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
            InputField(state = surname, valChange = {s -> surname.value = s }, name = stringResource(R.string.surname))
            Spacer(modifier = Modifier.padding(8.dp))
            InputField(state = name, valChange = {s -> name.value = s }, name = stringResource(R.string.name))
            Spacer(modifier = Modifier.padding(8.dp))
            InputField(state = patronymic, valChange = {s -> patronymic.value = s }, name = stringResource(R.string.patronymic))
            Spacer(modifier = Modifier.padding(8.dp))
            InputField(state = email, valChange = {s -> email.value = s }, name = stringResource(R.string.email))
            Spacer(modifier = Modifier.padding(8.dp))
            InputField(state = password, valChange = {s -> password.value = s }, name = stringResource(R.string.password), isPassword = true)
            Spacer(modifier = Modifier.padding(8.dp))
            InputField(state = confirmPassword, valChange = {s -> confirmPassword.value = s }, name = stringResource(R.string.confirm_password), isPassword = true)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(bottom = 25.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FirstButton(name = stringResource(R.string.register), state = inputNotEmpty, click = { navController.navigate(Screen.ScheduleScreen.route) } )
            Spacer(modifier = Modifier.padding(5.dp))
            SecondButton(name = stringResource(R.string.have_account), click = { navController.navigate(Screen.LoginScreen.route) })
        }
    }
}