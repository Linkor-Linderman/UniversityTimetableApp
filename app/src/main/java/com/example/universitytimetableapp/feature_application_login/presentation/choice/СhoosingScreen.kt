package com.example.universitytimetableapp.feature_application_login.presentation.choice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.universitytimetableapp.R

@Composable
fun ChoosingScreen(
    navController: NavController,
    viewModel: ChoosingViewModel = hiltViewModel()
) {
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

    val state by viewModel.uiState.observeAsState()

    if (state!!.mayNavigate) {
        viewModel.setDefaultState()
        navController.navigate(state!!.destinationString)
    }
    else if (state!!.isRoleChosen) {
        ChoosingGroupOrTeacher(viewModel)
    }
    else {
        ChoosingRole(navController, viewModel)
    }
}