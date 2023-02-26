package com.example.universitytimetableapp.feature_application_login.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universitytimetableapp.ui.theme.Jura
import com.example.universitytimetableapp.ui.theme.brown
import com.example.universitytimetableapp.ui.theme.greyLight
import com.example.universitytimetableapp.ui.theme.greyTint

@Composable
fun InputField(
    state: State<String>,
    valChange: (String) -> Unit,
    name: String,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = state.value,
        onValueChange = { valChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
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
            focusedBorderColor = greyLight,
            unfocusedBorderColor = greyLight,
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