package com.png.interview.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.png.interview.Utils
import com.png.interview.databinding.FragmentCurrentWeatherBinding
import com.png.interview.ui.InjectedFragment
import com.png.interview.weather.ui.binder.CurrentWeatherFragmentViewBinder
import com.png.interview.weather.ui.viewmodel.CurrentWeatherViewModel
import timber.log.Timber

class CurrentWeatherFragment : InjectedFragment() {
    var units: Int=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        units = Utils.getUnitsFromSharedPrefs(requireActivity())
        Timber.d("OnCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentCurrentWeatherBinding.inflate(inflater, container, false).apply {
            viewBinder = CurrentWeatherFragmentViewBinder(
                getViewModel(),
                requireActivity(),
                settingsAction = {
                    findNavController().navigate(CurrentWeatherFragmentDirections.actionCurrentWeatherFragmentToSettingsFragment())
                },
                forecastAction = {
                    findNavController().navigate(CurrentWeatherFragmentDirections.actionCurrentWeatherFragmentToWeatherForecastFragment())
                },
                Utils.getUnitsFromSharedPrefs(requireActivity())
            )
            this.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val newUnits = Utils.getUnitsFromSharedPrefs(requireActivity())

        val viewModel = ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)
        if (units != newUnits) {
            units = newUnits
            viewModel.resubmitSearch(units)
        }
    }
}