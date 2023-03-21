package com.dendron.easyweather.remote

import com.dendron.easyweather.domain.Weather
import com.dendron.easyweather.domain.WeatherRepository
import com.dendron.easyweather.remote.model.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteWeatherRepository @Inject constructor(private val api: WeatherApi) : WeatherRepository {
    override suspend fun getWeather(
        latitude: Double, longitude: Double
    ): Flow<Weather> = flow {
        emit(
            api.getWeather(
                latitude = latitude, longitude = longitude
            ).toDomain()
        )
    }
}