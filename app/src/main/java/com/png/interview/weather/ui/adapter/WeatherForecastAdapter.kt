package com.png.interview.weather.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.png.interview.databinding.ViewWeatherForecastListItemBinding
import com.png.interview.weather.ui.model.WeatherForecastDay

class WeatherForecastAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var weatherForecastList = emptyList<WeatherForecastDay>()

    fun setData(items: List<WeatherForecastDay>) {
        weatherForecastList = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            parent.context,
            ViewWeatherForecastListItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.bindItem(getItemAtPosition(position))
        }
    }
    private fun getItemAtPosition(position: Int): WeatherForecastDay {
        return weatherForecastList[position]
    }
    
    override fun getItemCount() = weatherForecastList.size


    class ViewHolder(private val context: Context, viewBinding: ViewWeatherForecastListItemBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        private var binding: ViewWeatherForecastListItemBinding? = viewBinding

        fun bindItem(item: WeatherForecastDay) {
            binding?.viewData = item
        }
    }
}