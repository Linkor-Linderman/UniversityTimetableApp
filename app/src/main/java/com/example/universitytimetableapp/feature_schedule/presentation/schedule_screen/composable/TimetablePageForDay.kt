package com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.composable.cards.SubjectTimeslotCard
import com.example.universitytimetableapp.feature_schedule.presentation.schedule_screen.composable.cards.WindowCard

@Composable
fun TimetablePageForDay(
    modifier: Modifier = Modifier,
    onClickToClassCard: () -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            WindowCard()
            Spacer(modifier = Modifier.height(20.dp))
            SubjectTimeslotCard(
                onClick = onClickToClassCard
            )
        }
    }
}