package com.dendron.easyweather.domain

data class Weather(
    val dailyUnits: DailyUnits,
    val maxTemperature: String,
    val minTemperature: String,
    val windSpeed: String,
    val weatherCode: Int,
)
