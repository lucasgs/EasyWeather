package com.dendron.easyweather.data.remote.model

data class DailyDto(
    val precipitation_sum: List<Double>,
    val rain_sum: List<Double>,
    val showers_sum: List<Double>,
    val snowfall_sum: List<Int>,
    val sunrise: List<String>,
    val sunset: List<String>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val time: List<String>,
    val uv_index_clear_sky_max: List<Double>,
    val uv_index_max: List<Double>,
    val weathercode: List<Int>,
    val windgusts_10m_max: List<Double>,
    val windspeed_10m_max: List<Double>
)