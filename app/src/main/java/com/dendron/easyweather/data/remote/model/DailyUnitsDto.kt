package com.dendron.easyweather.data.remote.model

data class DailyUnitsDto(
    val precipitation_sum: String,
    val rain_sum: String,
    val showers_sum: String,
    val snowfall_sum: String,
    val sunrise: String,
    val sunset: String,
    val temperature_2m_max: String,
    val temperature_2m_min: String,
    val time: String,
    val uv_index_clear_sky_max: String,
    val uv_index_max: String,
    val weathercode: String,
    val windgusts_10m_max: String,
    val windspeed_10m_max: String
)