package com.example.universitytimetableapp.feature_application_login.presentation.choice

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.ui.theme.*

@Composable
fun ChoosingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 50.dp)
    ) {
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.brown_background_form),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.95f)
                .padding(top = 35.dp),
            contentScale = ContentScale.FillBounds
        )
    }
    ChoosingGroupOrTeacher()
}

@Composable
fun ChoosingRole() {
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
            onClick = { },
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
            onClick = { },
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
        Spacer(modifier = Modifier.fillMaxHeight(0.4f))
        TextButton(
            onClick = { },
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

@Composable
fun ChoosingGroupOrTeacher() {
    var text by remember { mutableStateOf("") }
    val groupList = listOf("972101", "972102", "972103", "972105", "972110", "972201", "972202", "972203", "972205", "972210")
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.15f))
        Text(
            text =  stringResource(R.string.choosing_group),   //stringResource(R.string.what_your_name)  stringResource(R.string.choosing_teacher)
            modifier = Modifier.padding(start = 25.dp, end = 25.dp),
            fontFamily = Zekton,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            lineHeight = 34.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.padding(10.dp))
        TextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(start = 25.dp, end = 25.dp),
            placeholder = {
                Text(
                    text = stringResource(R.string.group_number),  //stringResource(R.string.full_name)
                    modifier = Modifier.fillMaxWidth(),
                    fontFamily = Jura,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp,
                    color = greyTint,
                    textAlign = TextAlign.Center
                )
            },
            textStyle = TextStyle(
                fontFamily = Jura,
                fontWeight = FontWeight.Normal,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            ),
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.padding(10.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.6f)
                .padding(start = 25.dp, end = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(groupList.size) { i ->
                Text(
                    text = groupList[i],
                    fontFamily = Jura,
                    fontWeight = if (i == 2) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 15.sp,
                    color = if (i == 2) Color.White else white90
                )
                Divider(color = white30, thickness = 1.dp)
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 20.dp, end = 15.dp)
        ) {
            TextButton(
                onClick = { },
                modifier = Modifier.size(180.dp, 40.dp)
            ) {
                Text(
                    text = stringResource(R.string.return_back),
                    fontFamily = Zekton,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
            TextButton(
                onClick = { showDialog = true },
                modifier = Modifier.size(150.dp, 40.dp),
            ) {
                Text(
                    text = stringResource(R.string.confirm),
                    fontFamily = Zekton,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
        }
    }

    if (showDialog) {
        Dialog(onDismissRequest = { }) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .padding(10.dp, 25.dp, 10.dp, 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.wait_confirmation_group, "fil_master@gmail.com"),
                    modifier = Modifier.width(250.dp),
                    fontFamily = Jura,
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.padding(15.dp))
                Button(
                    onClick = {
                        showDialog = false
                    },
                    modifier = Modifier
                        .size(180.dp, 40.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = brown
                    )
                ) {
                    Text(
                        text = stringResource(R.string.ok),
                        fontFamily = Zekton,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}