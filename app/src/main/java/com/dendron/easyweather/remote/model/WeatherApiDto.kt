package com.dendron.easyweather.remote.model

import com.dendron.easyweather.domain.Weather
import com.google.gson.annotations.SerializedName

data class WeatherApiModel(
    val daily: DailyDto,
    @SerializedName("daily_units")
    val dailyUnits: DailyUnitsDto,
    val elevation: Int,
    @SerializedName("generationtime_ms")
    val generationTimeMs: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    @SerializedName("timezone_abbreviation")
    val timezoneAbbreviation: String,
    @SerializedName("utc_offset_seconds")
    val utcOffsetSeconds: Int
)

fun WeatherApiModel.toDomain() = Weather(
    dailyUnits = dailyUnits.toDomain(),
    maxTemperature = daily.temperature_2m_max[1].toString(),
    minTemperature = daily.temperature_2m_min[1].toString(),
    windSpeed = daily.windspeed_10m_max[1].toString(),
    weatherCode = daily.weathercode[1]
)

fun DailyUnitsDto.toDomain() = com.dendron.easyweather.domain.DailyUnits(
    precipitation_sum = precipitation_sum,
    rain_sum = rain_sum,
    showers_sum = showers_sum,
    snowfall_sum = snowfall_sum,
    sunrise = sunrise,
    sunset = sunset,
    temperature_2m_max = temperature_2m_max,
    temperature_2m_min = temperature_2m_min,
    time = time,
    uv_index_clear_sky_max = uv_index_clear_sky_max,
    uv_index_max = uv_index_max,
    weathercode = weathercode,
    windgusts_10m_max = windgusts_10m_max,
    windspeed_10m_max = windspeed_10m_max,
)