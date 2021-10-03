package com.png.interview.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.png.interview.R
import com.png.interview.Utils
import com.png.interview.databinding.FragmentSettingsBinding
import com.png.interview.ui.InjectedFragment
import com.png.interview.weather.ui.binder.SettingsViewBinder
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : InjectedFragment() {

    companion object {
        const val PREFS = "WEATHER_SHARED_PREFS"
        const val UNITS_PREF = "UNITS_PREF"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentSettingsBinding.inflate(inflater, container, false).apply {
            viewBinder = SettingsViewBinder(
                requireActivity()
                )
            this.lifecycleOwner = viewLifecycleOwner
        }.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val units = Utils.getUnitsFromSharedPrefs(requireActivity())

        when (units) {
            1 -> rd_group.check(R.id.rb_imperial)
            else -> rd_group.check(R.id.rb_metric)
        }
    }
}