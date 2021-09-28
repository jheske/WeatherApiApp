package com.png.interview.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.png.interview.databinding.FragmentWeatherForecastBinding
import com.png.interview.ui.InjectedFragment
import com.png.interview.weather.ui.binder.WeatherForecastFragmentViewBinder

class WeatherForecastFragment : InjectedFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentWeatherForecastBinding.inflate(inflater, container,false).apply {
            viewBinder = WeatherForecastFragmentViewBinder(
                getViewModel(),
                requireActivity(),
            )
            this.lifecycleOwner = viewLifecycleOwner
        }.root
    }
}