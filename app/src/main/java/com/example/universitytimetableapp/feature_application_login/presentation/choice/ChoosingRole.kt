package com.example.universitytimetableapp.feature_application_login.presentation.choice

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.common.Constants
import com.example.universitytimetableapp.ui.theme.Jura
import com.example.universitytimetableapp.ui.theme.Zekton

@Composable
fun ChoosingRole(navController: NavController, viewModel: ChoosingViewModel) {
    val uiState by viewModel.uiState.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.15f))
        Text(
            text = stringResource(R.string.choosing_role),
            modifier = Modifier.padding(start = 25.dp, end = 25.dp),
            fontFamily = Zekton,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            lineHeight = 34.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.padding(25.dp))
        Button(
            onClick = { viewModel.choosingRole(Constants.STUDENT) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = stringResource(R.string.student),
                fontFamily = Jura,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
        Spacer(modifier = Modifier.padding(15.dp))
        Button(
            onClick = { viewModel.choosingRole(Constants.TEACHER) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                text = stringResource(R.string.teacher),
                fontFamily = Jura,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
        if (uiState!!.isClassroomShow) {
            Spacer(modifier = Modifier.padding(15.dp))
            Button(
                onClick = { viewModel.choosingRole(Constants.CLASSROOM) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, end = 25.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = stringResource(R.string.classroom),
                    fontFamily = Jura,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
            }
        }
        Spacer(modifier = Modifier.fillMaxHeight(if (uiState!!.isClassroomShow) 0.25f else 0.4f))
        TextButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .size(200.dp, 40.dp)
                .padding(start = 20.dp)
        ) {
            Text(
                text = stringResource(R.string.return_back),
                fontFamily = Zekton,
                fontWeight = FontWeight.Normal,
                fontSize = 15.sp,
                color = Color.White
            )
        }
    }
}