package com.png.interview.weather.ui.binder

import android.app.Activity
import android.widget.Toast
import com.png.interview.weather.ui.viewmodel.CurrentWeatherViewModel
import android.widget.ArrayAdapter

import android.widget.AutoCompleteTextView
import android.widget.ListAdapter

import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.AutoCompleteTextViewBindingAdapter
import com.png.interview.weather.ui.model.AutocompleteViewRepresentation


class CurrentWeatherFragmentViewBinder(
    private val viewModel: CurrentWeatherViewModel,
    private val activity: Activity,
    private val settingsAction: () -> Unit,
    private val forecastAction: () -> Unit,
    private val units: Int
) {
    val availableWeatherViewData = viewModel.availableCurrentWeatherLiveData
    val autocompleteTextList = viewModel.autocompleteTextList
    val isEmpty = viewModel.isEmptyVisible
    val isError = viewModel.isErrorVisible

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



