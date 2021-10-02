package com.png.interview.weather.ui.binder

import android.app.Activity
import android.widget.Toast
import com.png.interview.weather.ui.viewmodel.CurrentWeatherViewModel
import androidx.fragment.app.FragmentActivity
import com.png.interview.Keyboard


class CurrentWeatherFragmentViewBinder(
    private val viewModel: CurrentWeatherViewModel,
    private val activity: Activity,
    private val settingsAction: () -> Unit,
    private val forecastAction: () -> Unit,
    private val units: Int
) {
    val availableWeatherViewData = viewModel.availableCurrentWeatherLiveData
    val isEmpty = viewModel.isEmptyVisible
    val isError = viewModel.isErrorVisible
    val showData = viewModel.showData

    var input: String = ""

    fun refreshClicked() {
        goClicked()
    }

    fun seeForecastClicked() {
        forecastAction()
    }

    fun settingsClicked() {
        settingsAction()
    }

    fun goClicked() {
        Keyboard.dismiss(activity as FragmentActivity)

        if (input.isEmpty()) {
            Toast.makeText(activity, "Please Enter Query", Toast.LENGTH_LONG).show()
        } else if (input.length < 3) {
            Toast.makeText(activity, "Please Enter More than 3 Characters", Toast.LENGTH_LONG)
                .show()
        } else {
            viewModel.submitCurrentWeatherSearch(input, units)
        }
    }
}
