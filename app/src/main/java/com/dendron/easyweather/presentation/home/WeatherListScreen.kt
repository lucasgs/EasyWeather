package com.dendron.easyweather.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dendron.easyweather.presentation.home.components.CurrentWeatherConditions
import com.dendron.easyweather.presentation.home.components.CurrentWeatherImageAndDescription
import com.dendron.easyweather.presentation.ui.theme.Navy

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState()
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Navy)
    ) {
        state.value.data?.let { model ->
            CurrentWeatherImageAndDescription(model = model)
            CurrentWeatherConditions(model = model)
        }
    }
}