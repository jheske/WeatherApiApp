package com.png.interview

import android.app.Activity
import android.content.Context
import com.png.interview.weather.ui.fragment.SettingsFragment

class Utils {
    companion object {
        const val PREFS = "WEATHER_SHARED_PREFS"
        const val UNITS_PREF = "UNITS_PREF"

        fun saveUnitsToSharedPrefs(activity: Activity, units: Int) {
            val sharedPreference =
                activity.getSharedPreferences(SettingsFragment.PREFS, Context.MODE_PRIVATE)
            sharedPreference.edit()
                .putInt(SettingsFragment.UNITS_PREF, units)
                .apply()
        }

        fun getUnitsFromSharedPrefs(activity: Activity): Int {
            val sharedPreference =
                activity.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
            return sharedPreference.getInt(UNITS_PREF,1)
        }
    }
}