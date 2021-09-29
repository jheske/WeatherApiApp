package com.png.interview.weather.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.png.interview.R
import com.png.interview.Utils
import com.png.interview.databinding.FragmentSettingsBinding
import com.png.interview.ui.InjectedFragment
import com.png.interview.weather.ui.viewmodel.SettingsViewModel
import com.png.interview.weather.ui.viewmodel.WeatherForecastViewModel
import timber.log.Timber

class SettingsFragment : InjectedFragment() {

    companion object {
        const val PREFS = "WEATHER_SHARED_PREFS"
        const val UNITS_PREF = "UNITS_PREF"
    }

    lateinit var viewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentSettingsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_settings,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupObservers() {
        viewModel.radioButtonChecked.observe(
            viewLifecycleOwner, {
                Utils.saveUnitsToSharedPrefs(requireActivity(),it)
            }
        )
    }
}