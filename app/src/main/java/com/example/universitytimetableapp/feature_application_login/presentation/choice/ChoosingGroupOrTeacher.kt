package com.example.universitytimetableapp.feature_application_login.presentation.choice

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.common.Constants
import com.example.universitytimetableapp.feature_application_login.presentation.InfoDialog
import com.example.universitytimetableapp.ui.theme.*

@Composable
fun ChoosingGroupOrTeacher(viewModel: ChoosingViewModel) {
    val text by viewModel.search.collectAsState()
    val list by viewModel.listWithFilter.collectAsState()
    val chosenRole by viewModel.chosenRole.observeAsState()
    val chosenIndex by viewModel.choosingIndex.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.15f))
        Text(
            text = if (viewModel.case == Constants.REGISTER && chosenRole == Constants.TEACHER)
                stringResource(R.string.what_your_name)
            else if (chosenRole == Constants.TEACHER)
                stringResource(R.string.choosing_teacher)
            else
                stringResource(R.string.choosing_group),
            modifier = Modifier.padding(start = 25.dp, end = 25.dp),
            fontFamily = Zekton,
            fontWeight = FontWeight.Normal,
            fontSize = if (viewModel.case != Constants.REGISTER && chosenRole == Constants.TEACHER)
                24.sp
            else
                28.sp,
            lineHeight = 34.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.padding(10.dp))
        TextField(
            value = text,
            onValueChange = { viewModel.setSearch(it) },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .padding(start = 25.dp, end = 25.dp),
            placeholder = {
                Text(
                    text = if (chosenRole == Constants.TEACHER)
                        stringResource(R.string.full_name)
                    else
                        stringResource(R.string.group_number),
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
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
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
            items(list.size) { i ->
                Text(
                    text = list[i],
                    fontFamily = Jura,
                    fontWeight = if (i == chosenIndex) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 15.sp,
                    color = if (i == chosenIndex) Color.White else white90,
                    modifier = Modifier.clickable { viewModel.setChoosingIndex(i) }
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
                onClick = { viewModel.deselecting() },
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
                onClick = { viewModel.goToNextScreen() },
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

    if (viewModel.uiState.value!!.isShowDialog) {
        InfoDialog(
            text = stringResource(R.string.wait_confirmation_group, viewModel.studentEmail),
            close = { viewModel.goToNextScreen() }
        )
    }
}