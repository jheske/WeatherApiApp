package com.png.interview.weather.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.png.interview.databinding.FragmentWeatherForecastBinding
import com.png.interview.ui.InjectedFragment
import com.png.interview.weather.ui.adapter.WeatherForecastAdapter
import com.png.interview.weather.ui.binder.WeatherForecastFragmentViewBinder
import kotlinx.android.synthetic.main.fragment_weather_forecast.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
    }

    private fun setupAdapter() {
        val adapter = WeatherForecastAdapter()

        weatherForecastRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        weatherForecastRecyclerView.adapter = adapter
    }
}