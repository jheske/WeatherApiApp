package com.png.interview.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.png.interview.weather.domain.CreateCurrentWeatherRepFromQueryUseCase
import com.png.interview.weather.ui.model.CurrentWeatherViewRepresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val createCurrentWeatherRepFromQueryUseCase: CreateCurrentWeatherRepFromQueryUseCase
) : ViewModel() {

    private val _currentWeatherViewRepresentation = MutableStateFlow<CurrentWeatherViewRepresentation>(CurrentWeatherViewRepresentation.Empty)
    var units = 1

    fun submitCurrentWeatherSearch(query: String,units: Int) {
        viewModelScope.launch {
            _currentWeatherViewRepresentation.value = createCurrentWeatherRepFromQueryUseCase(query, units)
        }
    }

    val availableCurrentWeatherLiveData =
        _currentWeatherViewRepresentation
            .map {
                (it as? CurrentWeatherViewRepresentation.AvailableWeatherViewRep)?.data
            }
            .asLiveData()

    val showData =
        _currentWeatherViewRepresentation
            .map {
                (it as? CurrentWeatherViewRepresentation.AvailableWeatherViewRep)?.data != null
            }
            .asLiveData()

    val isEmptyVisible =
        _currentWeatherViewRepresentation
            .map { it is CurrentWeatherViewRepresentation.Empty }
            .asLiveData()

    val isErrorVisible =
        _currentWeatherViewRepresentation
            .map { it is CurrentWeatherViewRepresentation.Error }
            .asLiveData()
}