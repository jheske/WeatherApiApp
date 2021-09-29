package com.png.interview.weather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.png.interview.weather.domain.CreateAutocompleteRepFromQueryUseCase
import com.png.interview.weather.domain.CreateCurrentWeatherRepFromQueryUseCase
import com.png.interview.weather.ui.model.AutocompleteViewRepresentation
import com.png.interview.weather.ui.model.CurrentWeatherViewRepresentation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val createCurrentWeatherRepFromQueryUseCase: CreateCurrentWeatherRepFromQueryUseCase,
    private val createAutocompleteRepFromQueryUseCase: CreateAutocompleteRepFromQueryUseCase
) : ViewModel() {

    private val _currentWeatherViewRepresentation = MutableStateFlow<CurrentWeatherViewRepresentation>(CurrentWeatherViewRepresentation.Empty)
    private val _autocompleteViewRepresentation = MutableStateFlow<AutocompleteViewRepresentation>(AutocompleteViewRepresentation.Empty)

    var units = 1

    fun submitCurrentWeatherSearch(query: String,units: Int) {
        viewModelScope.launch {
            _currentWeatherViewRepresentation.value = createCurrentWeatherRepFromQueryUseCase(query, units)
        }
    }

    fun submitAutocompleteSearch(query: String,units: Int) {
        viewModelScope.launch {
            _autocompleteViewRepresentation.value = createAutocompleteRepFromQueryUseCase(query)
        }
    }

    val autocompleteTextList =
        _autocompleteViewRepresentation
            .map {
                (it as? AutocompleteViewRepresentation.AutocompleteViewRep)?.data
            }
            .asLiveData()

    val availableCurrentWeatherLiveData =
        _currentWeatherViewRepresentation
            .map {
                (it as? CurrentWeatherViewRepresentation.AvailableWeatherViewRep)?.data
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

    fun setupUnits(newUnits: Int) {
        if (newUnits != units) {
            val currentWeather = availableCurrentWeatherLiveData.value
            units = newUnits
        }
    }
}