package com.dendron.easyweather.data.remote

import com.dendron.easyweather.data.remote.model.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast?hourly=temperature_2m&daily=temperature_2m_max,temperature_2m_min,windspeed_10m_max&current_weather=true&past_days=1&forecast_days=3&timezone=auto")
    suspend fun getCurrentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): WeatherDto
}