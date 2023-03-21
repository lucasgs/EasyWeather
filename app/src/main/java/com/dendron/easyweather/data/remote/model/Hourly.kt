package com.dendron.easyweather.data.remote.model


import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("temperature_2m")
    val temperature2m: List<Double>,
    @SerializedName("time")
    val time: List<String>
)