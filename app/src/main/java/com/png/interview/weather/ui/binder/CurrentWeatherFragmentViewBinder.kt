package com.png.interview.weather.ui.binder

import android.app.Activity
import android.widget.Toast
import com.png.interview.weather.ui.viewmodel.CurrentWeatherViewModel

class CurrentWeatherFragmentViewBinder(
    private val viewModel: CurrentWeatherViewModel,
    private val activity: Activity,
    private val settingsAction: () -> Unit,
    private val forecastAction: () -> Unit
) {

    val availableWeatherViewData = viewModel.availableCurrentWeatherLiveData
    val isEmpty = viewModel.isEmptyVisible
    val isError = viewModel.isErrorVisible

    var input: String = "30024"

    fun refreshClicked() {
        Toast.makeText(activity, "Refresh Clicked TODO", Toast.LENGTH_LONG).show()
        goClicked()
    }

    fun seeForecastClicked() {
        forecastAction()
    }

    fun settingsClicked() {
        settingsAction()
    }

    fun goClicked() {
        if (input.isEmpty()) {
            Toast.makeText(activity, "Please Enter Query", Toast.LENGTH_LONG).show()
        } else if (input.length < 3) {
            Toast.makeText(activity, "Please Enter More than 3 Characters", Toast.LENGTH_LONG)
                .show()
        } else {
            viewModel.submitCurrentWeatherSearch(input)
        }
    }
}