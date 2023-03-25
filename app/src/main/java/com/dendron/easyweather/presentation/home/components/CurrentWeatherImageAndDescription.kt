package com.dendron.easyweather.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dendron.easyweather.presentation.home.WeatherUiModel
import com.dendron.easyweather.presentation.ui.theme.BlueLight
import com.dendron.easyweather.presentation.ui.theme.WhiteLight

@Composable
fun CurrentWeatherImageAndDescription(model: WeatherUiModel) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .padding(50.dp)
    ) {
        Image(
            painter = painterResource(id = model.weatherIcon),
            contentDescription = null,
            colorFilter = ColorFilter.tint(BlueLight),
            modifier = Modifier
                .size(60.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            model.descriptionText,
            color = WhiteLight,
            fontSize = MaterialTheme.typography.h5.fontSize,
        )
    }
}