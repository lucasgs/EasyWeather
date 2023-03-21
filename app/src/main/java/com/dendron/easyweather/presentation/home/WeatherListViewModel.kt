package com.dendron.easyweather.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dendron.easyweather.domain.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherListViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(WeatherListState())
    val state = _state.asStateFlow()

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch {
            weatherRepository.getWeather(
                latitude =-38.00,
                longitude=-57.56
            ).onEach { weather ->
                val model = WeatherUiModel(
//                    descriptionText = "It's warmer this afternoon than yesterday afternoon.",
                    descriptionText = getWeatherDescription(weather.weatherCode),
                    windText = "${weather.windSpeed} ${weather.dailyUnits.windspeed_10m_max}",
                    temperatureText = "${weather.minTemperature}° / ${weather.maxTemperature}°"
                )
                _state.value = WeatherListState(data = model)
            }.stateIn(viewModelScope)
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
            else -> ""
        }
}