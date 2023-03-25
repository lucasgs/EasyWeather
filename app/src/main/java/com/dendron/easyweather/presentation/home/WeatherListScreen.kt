package com.dendron.easyweather.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.dendron.easyweather.presentation.home.components.CurrentWeatherConditions
import com.dendron.easyweather.presentation.home.components.CurrentWeatherImageAndDescription
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: WeatherListViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState()
    val refreshScope = rememberCoroutineScope()

    fun refresh() = refreshScope.launch {
        viewModel.fetchData()
    }

    val refreshState = rememberPullRefreshState(state.value.isLoading, ::refresh)

    state.value.data?.let { model ->
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = modifier
                .pullRefresh(refreshState)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    CurrentWeatherImageAndDescription(model = model)
                }
                item {
                    CurrentWeatherConditions(model = model)
                }
            }
            PullRefreshIndicator(
                state.value.isLoading,
                refreshState,
                Modifier.align(Alignment.TopCenter)
            )
        }
    }
}