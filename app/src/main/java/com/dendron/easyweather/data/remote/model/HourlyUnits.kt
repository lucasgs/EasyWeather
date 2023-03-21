package com.dendron.easyweather.data.remote.model


import com.google.gson.annotations.SerializedName

data class HourlyUnits(
    @SerializedName("temperature_2m")
    val temperature2m: String,
    @SerializedName("time")
    val time: String
)