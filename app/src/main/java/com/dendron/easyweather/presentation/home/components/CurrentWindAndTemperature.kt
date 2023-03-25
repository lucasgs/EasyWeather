package com.dendron.easyweather.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dendron.easyweather.R
import com.dendron.easyweather.presentation.home.WeatherUiModel

@Composable
fun CurrentWeatherConditions(
    model: WeatherUiModel
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        CurrentWeatherItem(iconId = R.drawable.wind, text = model.windText)
        CurrentWeatherItem(iconId = R.drawable.thermometer, text = model.temperatureText)
    }
}