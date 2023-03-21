package com.dendron.easyweather.data.remote.model


import com.google.gson.annotations.SerializedName

data class CurrentWeather(
    @SerializedName("temperature")
    val temperature: Double,
    @SerializedName("time")
    val time: String,
    @SerializedName("weathercode")
    val weathercode: Int,
    @SerializedName("winddirection")
    val winddirection: Int,
    @SerializedName("windspeed")
    val windspeed: Double
)