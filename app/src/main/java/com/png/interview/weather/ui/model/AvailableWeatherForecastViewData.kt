package com.png.interview.weather.ui.model

data class AvailableWeatherForecastViewData(
    val name: String,
    val date: String,
    val minTemperature: String,
    val maxTemperature: String,
    val condition: String,
    val windDirection: String,
    val windSpeed: String
)