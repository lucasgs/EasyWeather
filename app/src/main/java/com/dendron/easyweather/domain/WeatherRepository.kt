package com.dendron.easyweather.domain

interface WeatherRepository {
    suspend fun getCurrentWeather(latitude: Double, longitude: Double): Weather
}