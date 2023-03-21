package com.dendron.easyweather.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dendron.easyweather.R
import com.dendron.easyweather.presentation.ui.theme.BlueLight
import com.dendron.easyweather.presentation.ui.theme.Navy
import com.dendron.easyweather.presentation.ui.theme.WhiteLight

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: WeatherListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Navy)
    ) {
        state.value.data?.let {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxHeight(0.8f)
                        .padding(50.dp)
                ) {
                    Image(
                        painter = painterResource(id = it.weatherIcon),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(BlueLight),
                        modifier = Modifier
                            .size(60.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        it.descriptionText,
                        color = WhiteLight,
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        modifier = Modifier
                    )
                }
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement
                            .spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.wind),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(BlueLight),
                        )
                        Text(text = it.windText, color = Color.White)
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.thermometer),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(BlueLight),
                        )
                        Text(text = it.temperatureText, color = Color.White)
                    }
                }
            }
        }
    }
}