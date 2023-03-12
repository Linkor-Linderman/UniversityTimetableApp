package com.example.universitytimetableapp.feature_profile.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universitytimetableapp.ui.theme.Jura
import com.example.universitytimetableapp.ui.theme.brown
import com.example.universitytimetableapp.ui.theme.greyMedium


@Composable
fun ProfileLeftText(name: String) {
    Text(
        text = name,
        fontFamily = Jura,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp
    )
}

@Composable
fun ProfileRightText(name: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(greyMedium, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = name,
            fontFamily = Jura,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
    }
}

@Composable
fun ProfileButton(
    name: String,
    click: () -> Unit,
) {
    Button(
        onClick = click,
        modifier = Modifier
            .heightIn(min = 50.dp),
        border = BorderStroke(2.dp, brown),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ),
        shape = RoundedCornerShape(15.dp)
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
fun FillProfileButton(
    name: String,
    click: () -> Unit,
) {
    Button(
        onClick = click,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 40.dp, end = 40.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = brown
        ),
        shape = RoundedCornerShape(15.dp)
    ) {
        Text(
            text = name,
            textAlign = TextAlign.Center,
            fontFamily = Jura,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp,
            color = Color.White
        )
    }
}

@Composable
fun ChangePasswordField(
    state: State<String>,
    valChange: (String) -> Unit
) {
    OutlinedTextField(
        value = state.value,
        onValueChange = { valChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .height(47.dp),
        textStyle = TextStyle(
            fontFamily = Jura,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            color = Color.Black,
            letterSpacing = 5.sp
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = brown,
            unfocusedBorderColor = brown,
        ),
        singleLine = true,
        shape = RoundedCornerShape(6.dp),
        visualTransformation = PasswordVisualTransformation()
    )
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