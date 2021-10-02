package com.png.interview

import android.app.Activity
import android.content.Context
import com.png.interview.weather.ui.fragment.SettingsFragment
import java.text.SimpleDateFormat
import java.util.*

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

        /**
         * There is a more recent DateUtils class, which I could also have
         * used here.
         */
        fun reformatForecastDate(date: String): String {
            var format = SimpleDateFormat("yyyy-mm-dd",Locale.US)
            val newDate = format.parse(date)

            return newDate?.let {
                format = SimpleDateFormat("EEEE, MMMM dd", Locale.US)
                return format.format(it)
            } ?: run {date}
        }
    }
}