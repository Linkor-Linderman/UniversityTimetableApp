package com.example.universitytimetableapp.feature_schedule.presentation.composable


import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.universitytimetableapp.R
import com.example.universitytimetableapp.ui.theme.Zekton
import com.example.universitytimetableapp.ui.theme.brown
import com.example.universitytimetableapp.ui.theme.greyForSelectedDay
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Preview
@Composable
fun TimeTableForWeek(
    modifier: Modifier = Modifier
) {
    val numberOfDaysOfTheWeek = listOf(1, 2, 3, 4, 5, 6, 7)
    val nameOfDaysOfTheWeek = listOf(
        stringResource(id = R.string.monday),
        stringResource(id = R.string.tuesday),
        stringResource(id = R.string.wednesday),
        stringResource(id = R.string.thursday),
        stringResource(id = R.string.friday),
        stringResource(id = R.string.saturday),
        stringResource(id = R.string.sunday)
    )
    val pagerState = rememberPagerState(
        pageCount = nameOfDaysOfTheWeek.size,
        initialOffscreenLimit = 3,
        infiniteLoop = true,
        initialPage = 0,
    )
    val tabIndex = remember {
        mutableStateOf(pagerState.currentPage)
    }
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = modifier
    ) {
        TabRow(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp))
                .background(brown)
                .shadow(
                    AppBarDefaults.TopAppBarElevation,
                    RoundedCornerShape(bottomEnd = 20.dp, bottomStart = 20.dp)
                ),
            selectedTabIndex = tabIndex.value,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage])
                )
            },
            backgroundColor = brown,
            contentColor = Color.Transparent
        ) {
            IconButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.left_arrow),
                    contentDescription = "left arrow",
                    tint = Color.White
                )
            }
            nameOfDaysOfTheWeek.forEachIndexed { index, day ->
                val color = remember {
                    Animatable(Color.Transparent)
                }
                LaunchedEffect(
                    pagerState.currentPage == index
                ) {
                    color.animateTo(
                        if (pagerState.currentPage == index)
                            greyForSelectedDay
                        else
                            Color.Transparent
                    )
                }

                Tab(
                    modifier = Modifier,
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(
                                page = index,
                                pageOffset = 0f,
                                animationSpec = tween(350),
                                skipPages = true
                            )
                        }
                    },
                    icon = {
                        Column(
                            modifier = Modifier.width(17.dp),
                        ) {
                            Row(
                                modifier = Modifier.width(17.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = nameOfDaysOfTheWeek[index],
                                    fontSize = 10.sp,
                                    fontFamily = Zekton,
                                    color = Color.White,
                                    maxLines = 1,
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Box(
                                modifier = Modifier
                                    .width(17.dp)
                                    .height(21.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(
                                        color.value,
                                        RoundedCornerShape(5.dp)
                                    ),
                                contentAlignment = Center
                            ) {
                                Text(
                                    text = numberOfDaysOfTheWeek[index].toString(),
                                    fontSize = 15.sp,
                                    fontFamily = Zekton,
                                    color = Color.White,
                                )
                            }
                        }
                    }
                )
            }
            IconButton(
                onClick = { /*TODO*/ },
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.right_arrow),
                    contentDescription = "right arrow",
                    tint = Color.White
                )
            }
        }

        HorizontalPager(
            state = pagerState,
        ) { index ->
            TimetablePageForDay(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 18.dp, vertical = 20.dp)
            )
        }
    }
}