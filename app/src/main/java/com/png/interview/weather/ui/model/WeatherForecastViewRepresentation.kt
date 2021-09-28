package com.png.interview.weather.ui.model

sealed class WeatherForecastViewRepresentation {
    class WeatherForecastViewRep(val data: AvailableWeatherForecastViewData) : WeatherForecastViewRepresentation()
    object Empty : WeatherForecastViewRepresentation()
    object Error : WeatherForecastViewRepresentation()
}
