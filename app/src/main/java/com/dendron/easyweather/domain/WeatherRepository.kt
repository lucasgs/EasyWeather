package com.dendron.easyweather.domain

import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getWeather(latitude: Double, longitude: Double): Flow<Weather>
}