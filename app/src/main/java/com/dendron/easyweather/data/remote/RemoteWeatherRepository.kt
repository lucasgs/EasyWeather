package com.dendron.easyweather.data.remote

import com.dendron.easyweather.data.remote.model.toDomain
import com.dendron.easyweather.domain.Weather
import com.dendron.easyweather.domain.WeatherRepository
import javax.inject.Inject

class RemoteWeatherRepository @Inject constructor(private val api: WeatherApi) : WeatherRepository {
    override suspend fun getWeather(
        latitude: Double, longitude: Double
    ): Weather =
        api.getWeather(
            latitude = latitude, longitude = longitude
        ).toDomain()
}