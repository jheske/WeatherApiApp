package com.png.interview.weather.ui.model

data class AvailableWeatherForecastViewData(
   val weatherForecastList: List<WeatherForecastDay>
)

data class WeatherForecastDay(
    val date: String,
    val minTemperatureF: String,
    val maxTemperatureF: String,
    val minTemperatureC: String,
    val maxTemperatureC: String,
    val condition: String,
    val windSpeedKph: String,
    val windSpeedMph: String
)

