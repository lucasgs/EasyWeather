package com.dendron.easyweather.remote

import com.dendron.easyweather.remote.model.WeatherApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("forecast?daily=weathercode,temperature_2m_max,temperature_2m_min,sunrise,sunset,uv_index_max,uv_index_clear_sky_max,precipitation_sum,rain_sum,showers_sum,snowfall_sum,windspeed_10m_max,windgusts_10m_max&past_days=1&forecast_days=1&timezone=auto")
    suspend fun getWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): WeatherApiModel
}