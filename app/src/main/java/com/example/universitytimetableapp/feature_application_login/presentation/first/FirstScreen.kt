package com.example.universitytimetableapp.feature_application_login.presentation.first

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.common.Constants
import com.example.universitytimetableapp.common.Screen
import com.example.universitytimetableapp.ui.theme.Jura
import com.example.universitytimetableapp.ui.theme.Zekton
import com.example.universitytimetableapp.ui.theme.brown

@Composable
fun FirstScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brown)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.89f)) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.white_background_form),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                contentScale = ContentScale.FillBounds
            )
        }

        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.8f)
                    .padding(top = 80.dp)
            ) {
                Text(
                    text = stringResource(R.string.welcome),
                    modifier = Modifier.padding(start = 25.dp),
                    fontFamily = Zekton,
                    fontWeight = FontWeight.Normal,
                    fontSize = 25.sp,
                    lineHeight = 35.sp
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = stringResource(R.string.app_about),
                    modifier = Modifier
                        .padding(start = 30.dp)
                        .fillMaxWidth(0.6f),
                    fontFamily = Jura,
                    fontWeight = FontWeight.Light,
                    fontSize = 17.sp
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = stringResource(R.string.app_about_type),
                    modifier = Modifier.padding(start = 40.dp),
                    fontFamily = Jura,
                    fontWeight = FontWeight.Light,
                    fontSize = 17.sp,
                    lineHeight = 27.sp
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { navController.navigate(Screen.LoginScreen.route) },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    shape = RoundedCornerShape(15.dp)
                ) {
                    Text(
                        text = stringResource(R.string.authorize),
                        fontFamily = Jura,
                        fontWeight = FontWeight.Medium,
                        fontSize = 17.sp
                    )
                }

                Spacer(modifier = Modifier.padding(8.dp))

                TextButton(
                    onClick = { navController.navigate("${Screen.ChoosingScreen.route}/${Constants.CHANGE_INIT_CHOICE_OR_GUEST}") },
                    modifier = Modifier.size(180.dp, 40.dp)
                ) {
                    Text(
                        text = stringResource(R.string.enter_how_guest),
                        fontFamily = Jura,
                        fontWeight = FontWeight.Light,
                        fontSize = 17.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}