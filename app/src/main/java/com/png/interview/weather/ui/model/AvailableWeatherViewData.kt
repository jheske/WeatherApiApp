package com.png.interview.weather.ui.model

data class AvailableWeatherViewData(
    val name: String,
    val date: String,
    val temperature: String,
    val condition: String,
    val iconUrl: String,
    val windDirection: String,
    val windSpeed: String
)