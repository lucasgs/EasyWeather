package com.dendron.easyweather.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dendron.easyweather.domain.WeatherRepository
import com.dendron.easyweather.domain.location.LocationProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationProvider: LocationProvider,
) : ViewModel() {

    private val _state = MutableStateFlow(WeatherListState())
    val state = _state.asStateFlow()

    fun fetchData() {
        viewModelScope.launch {
            val currentLocation = locationProvider.getCurrentLocation()
            currentLocation?.let { location ->
                val weather = weatherRepository.getWeather(
                    latitude = location.latitude,
                    longitude = location.longitude,
                )
                val model = WeatherUiModel(
//                    descriptionText = "It's warmer this afternoon than yesterday afternoon.",
                    descriptionText = getWeatherDescription(weather.weatherCode),
                    windText = "${weather.windSpeed} ${weather.dailyUnits.windspeed_10m_max}",
                    temperatureText = "${weather.minTemperature}° / ${weather.maxTemperature}°"
                )
                _state.value = WeatherListState(data = model)
            }
        }
    }

    private fun getWeatherDescription(weatherCode: Int) =
        when (weatherCode) {
            0 -> "Clear sky"
            1, 2, 3 -> "Mainly clear, partly cloudy, and overcast"
            45, 48 -> "Fog and depositing rime fog"
            51, 53, 55 -> "Drizzle: Light, moderate, and dense intensity"
            56, 57 -> "Freezing Drizzle: Light and dense intensity"
            61, 63, 65 -> "Rain: Slight, moderate and heavy intensity"
            66, 67 -> "Freezing Rain: Light and heavy intensity"
            71, 73, 75 -> "Snow fall: Slight, moderate, and heavy intensity"
            77 -> "Snow grains"
            80, 81, 82 -> "Rain showers: Slight, moderate, and violent"
            85, 86 -> "Snow showers slight and heavy"
            95 -> "Thunderstorm: Slight or moderate"
            96, 99 -> "Thunderstorm with slight and heavy hail"
            else -> "Nothing to say :("
        }
}