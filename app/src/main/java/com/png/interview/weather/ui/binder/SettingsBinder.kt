package com.png.interview.weather.ui.binder

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import com.png.interview.Utils
import com.png.interview.weather.ui.viewmodel.SettingsViewModel

class SettingsBinder(
    private val activity: Activity,
) {
    val radioButtonValue: MutableLiveData<Int> = MutableLiveData(Utils.getUnitsFromSharedPrefs(activity))
}