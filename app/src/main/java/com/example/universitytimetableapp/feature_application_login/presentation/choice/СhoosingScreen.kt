package com.example.universitytimetableapp.feature_application_login.presentation.choice

import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.common.Constants
import com.example.universitytimetableapp.feature_application_login.presentation.AppProgressIndicator

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

    if (state!!.isLoading) {
        AppProgressIndicator(Color.White)
    }
    if (state!!.isShowMessage) {
        Toast.makeText(LocalContext.current, state!!.message, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.FILL_HORIZONTAL, 0, 0)
        }.show()
        viewModel.setDefaultState()
    }
    if (state!!.mayNavigate) {
        viewModel.setDefaultState()
        if (viewModel.case == Constants.CHANGE_GROUP) {
            navController.popBackStack()
        }
        else {
            navController.navigate(state!!.destinationString) {
                if (viewModel.case == Constants.CHANGE_INIT_CHOICE_OR_GUEST) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                }
            }
        }

    }
    else if (state!!.isRoleChosen) {
        ChoosingGroupOrTeacher(viewModel)
    }
    else {
        ChoosingRole(navController, viewModel)
    }
}