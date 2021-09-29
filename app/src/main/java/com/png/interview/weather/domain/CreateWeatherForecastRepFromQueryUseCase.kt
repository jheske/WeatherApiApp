package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.model.AvailableWeatherForecastViewData
import com.png.interview.weather.ui.model.WeatherForecastDay
import com.png.interview.weather.ui.model.WeatherForecastViewRepresentation
import javax.inject.Inject

interface CreateWeatherForecastRepFromQueryUseCase {
    suspend operator fun invoke(query: String, units: Int): WeatherForecastViewRepresentation
}

class DefaultCreateWeatherForecastRepFromQueryUseCase @Inject constructor(
    private val getWeatherForecastDataUseCase: GetWeatherForecastDataUseCase
) : CreateWeatherForecastRepFromQueryUseCase {
    override suspend fun invoke(query: String, units: Int): WeatherForecastViewRepresentation {
        return when (val result = getWeatherForecastDataUseCase(query)) {
            is NetworkResponse.Success -> {
                val list = arrayListOf<WeatherForecastDay>()
                result.body.forecast.forecastday.forEach {
                    list.add(
                        WeatherForecastDay(
                            date = it.date,
                            minTemperature = if (units == 1) "${it.day.mintemp_f} F" else "${it.day.mintemp_c} C",
                            maxTemperature = if (units == 1) "${it.day.maxtemp_f} F" else "${it.day.maxtemp_c} C",
                            condition = it.day.condition.text,
                            windSpeed = if (units == 1) "${it.day.maxwind_mph} MPH" else "${it.day.maxwind_kph} KPH"
                        )
                    )
                }
                WeatherForecastViewRepresentation.WeatherForecastViewRep(
                    AvailableWeatherForecastViewData(
                        list
                    )
                )
            }
            else -> {
                WeatherForecastViewRepresentation.Error
            }
        }
    }
}