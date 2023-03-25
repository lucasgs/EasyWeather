package com.dendron.easyweather.presentation.home

import app.cash.turbine.test
import com.dendron.easyweather.MainDispatcherRule
import com.dendron.easyweather.common.Resource
import com.dendron.easyweather.domain.Weather
import com.dendron.easyweather.domain.WeatherRepository
import com.dendron.easyweather.domain.WeatherUnits
import com.dendron.easyweather.domain.location.LocationData
import com.dendron.easyweather.domain.location.LocationProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class WeatherListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var weatherRepository: WeatherRepository

    @Mock
    private lateinit var locationProvider: LocationProvider

    private lateinit var viewModel: WeatherListViewModel


    @Before
    fun setUp() {
        viewModel = WeatherListViewModel(
            weatherRepository = weatherRepository,
            locationProvider = locationProvider
        )
    }

    @Test
    fun `fetchData should emit success if there's a location and repository returns ok`() =
        runTest {

            val expectedWeather = fakeWeather
            val expectedUiModel = fakeWeatherUiModel
            val expectedSuccess = WeatherListState(data = expectedUiModel)

            whenever(locationProvider.getCurrentLocation()).thenReturn(
                LocationData(LAT, LONG)
            )

            whenever(weatherRepository.getCurrentWeather(LAT, LONG)).thenReturn(
                flow {
                    emit(Resource.Success(data = expectedWeather))
                }
            )

            viewModel.fetchData()
            viewModel.state.test {
                assertEquals(expectedSuccess, awaitItem())
            }
        }


    @Test
    fun `fetchData should emit error if there's a location and repository returns an error`() =
        runTest {

            val expectedErrorMessage = "Error"
            val expectedError = WeatherListState(error = expectedErrorMessage)

            whenever(locationProvider.getCurrentLocation()).thenReturn(
                LocationData(LAT, LONG)
            )

            whenever(weatherRepository.getCurrentWeather(LAT, LONG)).thenReturn(
                flow {
                    emit(Resource.Error(message = expectedErrorMessage))
                }
            )

            viewModel.fetchData()
            viewModel.state.test {
                assertEquals(expectedError, awaitItem())
            }
        }

    @Test
    fun `fetchData should emit loading if there's a location and repository returns that is loading`() =
        runTest {
            val expectedLoading = WeatherListState(isLoading = true)

            whenever(locationProvider.getCurrentLocation()).thenReturn(
                LocationData(LAT, LONG)
            )

            whenever(weatherRepository.getCurrentWeather(LAT, LONG)).thenReturn(
                flow {
                    emit(Resource.Loading())
                }
            )

            viewModel.fetchData()
            viewModel.state.test {
                assertEquals(expectedLoading, awaitItem())
            }
        }

    @Test
    fun `fetchData should not emit anything if there's no location`() = runTest {

        val expectedError = WeatherListState()

        whenever(locationProvider.getCurrentLocation()).thenReturn(null)

        viewModel.fetchData()
        viewModel.state.test {
            assertEquals(expectedError, awaitItem())
        }
    }

    @Test
    fun `toUiModel should return a valid UiModel if Weather is valid`() {
        val actualUiModel = fakeWeather.toUiModel()
        assertEquals(fakeWeatherUiModel, actualUiModel)
    }

    companion object {
        const val LAT = 1.0
        const val LONG = 1.0

        val fakeWeatherUiModel = WeatherUiModel(
            descriptionText = "Mainly clear, partly cloudy, and overcast",
            windText = "50.0 km/h SE",
            temperatureText = "2.0° / 10.0° / 1.0°",
            weatherIcon = 2131165314,
        )

        val fakeWeather = Weather(
            weatherUnit = WeatherUnits(
                temperature = "",
                wind = "km/h"
            ),
            currentTemperature = 10.0,
            maxTemperature = 1.0,
            minTemperature = 2.0,
            windSpeed = 50.0,
            windDirection = 120,
            weatherCode = 1,
        )
    }
}