package com.png.interview.weather.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SettingsViewModel @Inject constructor() : ViewModel() {
    var radioButtonChecked = MutableLiveData<Int>()

    fun initRadioButton(units: Int) {
        radioButtonChecked.value = units
    }
}