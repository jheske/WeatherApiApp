package com.png.interview.weather.ui.model

data class AvailableWeatherForecastViewData(
   val weatherForecastList: List<WeatherForecastDay>
)

data class WeatherForecastDay(
    val date: String,
    val minTemperature: String,
    val maxTemperature: String,
    val condition: String,
    val windSpeed: String
)

