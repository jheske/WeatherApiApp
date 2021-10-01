package com.png.interview.weather.ui.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
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
import com.png.interview.weather.ui.viewmodel.WeatherForecastViewModel
import kotlinx.android.synthetic.main.activity_main.mainNavigationFragment
import kotlinx.android.synthetic.main.fragment_current_weather.*
import timber.log.Timber
import android.widget.Toast




class CurrentWeatherFragment : InjectedFragment() {

    lateinit var viewModel: CurrentWeatherViewModel

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
        viewModel = ViewModelProvider(this).get(CurrentWeatherViewModel::class.java)
        setupAdapter()
        setupObserver()
    }

    private fun setupAdapter() {
        et_input.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString()
                if ((query.length >= 3) && (query != "null")) {
                    viewModel.submitAutocompleteSearch(query)
                }
            }
        })
    }

    private fun setupObserver() {
        viewModel.autocompleteTextList.observe(viewLifecycleOwner,{
            Timber.d("${it?.autocompleteList?.size}")
        })
    }
}