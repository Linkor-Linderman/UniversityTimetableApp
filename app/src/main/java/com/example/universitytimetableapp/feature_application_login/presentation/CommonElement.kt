package com.example.universitytimetableapp.feature_application_login.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.universitytimetableapp.ui.theme.*

@Composable
fun InputField(
    state: State<String?>,
    valChange: (String) -> Unit,
    name: String,
    isPassword: Boolean = false,
    isEnable: Boolean = true
) {
    OutlinedTextField(
        value = state.value ?: "",
        onValueChange = { valChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        enabled = isEnable,
        placeholder = {
            Text(
                text = name,
                fontFamily = Jura,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                color = greyTint
            )
        },
        textStyle = TextStyle(
            fontFamily = Jura,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.White,
            letterSpacing = if (isPassword) 5.sp else 0.sp
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = if (isEnable) brown else white10,
            focusedBorderColor = greyLight,
            unfocusedBorderColor = greyLight,
            disabledBorderColor = white10
        ),
        shape = RoundedCornerShape(8.dp),
        visualTransformation = if (isPassword)
            PasswordVisualTransformation()
        else
            VisualTransformation.None
    )
}

@Composable
fun FirstButton(
    name: String,
    state: State<Boolean>,
    click: () -> Unit
) {
    Button(
        onClick = click,
        modifier = Modifier
            .fillMaxWidth(0.75f)
            .height(50.dp),
        border = if (state.value) {
            null
        } else {
            BorderStroke(2.dp, brown)
        },
        colors = ButtonDefaults.buttonColors(
            disabledBackgroundColor = Color.White,
            backgroundColor = brown
        ),
        shape = RoundedCornerShape(15.dp),
        enabled = state.value
    ) {
        Text(
            text = name,
            textAlign = TextAlign.Center,
            fontFamily = Jura,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp,
            color = if (state.value) {
                Color.White
            } else {
                Color.Black
            }
        )
    }
}

@Composable
fun SecondButton(
    name: String,
    click: () -> Unit
) {
    TextButton(
        onClick =  click ,
        modifier = Modifier
    ) {
        Text(
            text = name,
            textAlign = TextAlign.Center,
            fontFamily = Jura,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            color = Color.Black
        )
    }
}

@Composable
fun InfoDialog(
    text: String,
    close: () -> Unit,
) {
    Dialog(onDismissRequest = { }) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(10.dp, 25.dp, 10.dp, 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                modifier = Modifier.width(250.dp),
                fontFamily = Jura,
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(15.dp))
            Button(
                onClick = close,
                modifier = Modifier
                    .size(180.dp, 40.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = brown
                )
            ) {
                Text(
                    text = stringResource(com.example.universitytimetableapp.R.string.ok),
                    fontFamily = Zekton,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun AppProgressIndicator(color: Color) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(100.dp), color = color)
    }
}