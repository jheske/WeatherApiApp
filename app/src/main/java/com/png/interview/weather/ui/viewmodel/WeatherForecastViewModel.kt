package com.png.interview.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.png.interview.weather.domain.CreateWeatherForecastRepFromQueryUseCase
import com.png.interview.weather.ui.model.WeatherForecastViewRepresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherForecastViewModel @Inject constructor(
    private val createWeatherForecastRepFromQueryUseCase: CreateWeatherForecastRepFromQueryUseCase
) : ViewModel() {

    private val _weatherForecastViewRepresentation = MutableStateFlow<WeatherForecastViewRepresentation>(WeatherForecastViewRepresentation.Empty)

    init {
        submitWeatherForecastSearch("30024")
    }

    fun submitWeatherForecastSearch(query: String) {
        viewModelScope.launch {
            _weatherForecastViewRepresentation.value = createWeatherForecastRepFromQueryUseCase(query)
        }
    }

    val availableWeatherForecastLiveData =
        _weatherForecastViewRepresentation
            .map { (it as? WeatherForecastViewRepresentation.WeatherForecastViewRep)?.data }
            .asLiveData()

    val isEmptyVisible =
        _weatherForecastViewRepresentation
            .map { it is WeatherForecastViewRepresentation.Empty }
            .asLiveData()

    val isErrorVisible =
        _weatherForecastViewRepresentation
            .map { it is WeatherForecastViewRepresentation.Error }
            .asLiveData()
}