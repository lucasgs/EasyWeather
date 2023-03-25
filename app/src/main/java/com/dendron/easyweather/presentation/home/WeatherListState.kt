package com.dendron.easyweather.presentation.home

data class WeatherListState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: WeatherUiModel? = null,
)