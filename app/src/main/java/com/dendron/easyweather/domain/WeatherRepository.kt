package com.dendron.easyweather.domain

interface WeatherRepository {
    suspend fun getWeather(latitude: Double, longitude: Double): Weather
}