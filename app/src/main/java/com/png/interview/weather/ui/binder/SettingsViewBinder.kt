package com.png.interview.weather.ui.binder

import android.app.Activity
import com.png.interview.Utils


class SettingsViewBinder(
    private val activity: Activity
) {
    fun setImperial() {
        Utils.saveUnitsToSharedPrefs(activity,1)
    }

    fun setMetric() {
        Utils.saveUnitsToSharedPrefs(activity,2)
    }
}
