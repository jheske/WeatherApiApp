package com.png.interview.weather.ui.binder

import android.app.Activity
import com.png.interview.weather.ui.viewmodel.WeatherForecastViewModel

class WeatherForecastFragmentViewBinder(
    private val viewModel: WeatherForecastViewModel,
    private val activity: Activity,
) {
    val availableWeatherForecastViewData = viewModel.availableWeatherForecastLiveData
    val isEmpty = viewModel.isEmptyVisible
    val isError = viewModel.isErrorVisible
}