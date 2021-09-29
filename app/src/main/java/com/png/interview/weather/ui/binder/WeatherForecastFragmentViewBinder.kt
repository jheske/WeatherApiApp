package com.png.interview.weather.ui.binder

import android.app.Activity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.weather.ui.adapter.WeatherForecastAdapter
import com.png.interview.weather.ui.model.WeatherForecastDay
import com.png.interview.weather.ui.viewmodel.WeatherForecastViewModel

class WeatherForecastFragmentViewBinder(
    private val viewModel: WeatherForecastViewModel,
    private val activity: Activity,
) {
    val availableWeatherForecastViewData = viewModel.availableWeatherForecastLiveData
    val isEmpty = viewModel.isEmptyVisible
    val isError = viewModel.isErrorVisible
}

@BindingAdapter("data")
fun <T> setRecyclerViewData(recyclerView: RecyclerView, items: List<WeatherForecastDay>?) {
    items?.let {
        if (recyclerView.adapter is WeatherForecastAdapter) {
            (recyclerView.adapter as WeatherForecastAdapter).setData(items)
        }
    }
}