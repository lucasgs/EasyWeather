package com.dendron.easyweather.domain

import com.dendron.easyweather.common.Resource
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCurrentWeather(latitude: Double, longitude: Double): Flow<Resource<Weather>>
}