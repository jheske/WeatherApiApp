package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.model.AvailableWeatherForecastViewData
import com.png.interview.weather.ui.model.WeatherForecastDay
import com.png.interview.weather.ui.model.WeatherForecastViewRepresentation
import javax.inject.Inject

interface CreateWeatherForecastRepFromQueryUseCase {
    suspend operator fun invoke(query: String): WeatherForecastViewRepresentation
}

class DefaultCreateWeatherForecastRepFromQueryUseCase @Inject constructor(
    private val getWeatherForecastDataUseCase: GetWeatherForecastDataUseCase
) : CreateWeatherForecastRepFromQueryUseCase {
    override suspend fun invoke(query: String): WeatherForecastViewRepresentation {
        return when (val result = getWeatherForecastDataUseCase(query)) {
            is NetworkResponse.Success -> {
                val list = arrayListOf<WeatherForecastDay>()
                result.body.forecast.forecastday.forEach {
                    list.add(
                        WeatherForecastDay(
                            date = it.date,
                            minTemperatureF = "${it.day.mintemp_f} F",
                            maxTemperatureF = "${it.day.maxtemp_f} F",
                            minTemperatureC = "${it.day.mintemp_c} C",
                            maxTemperatureC = "${it.day.maxtemp_c} C",
                            condition = it.day.condition.text,
                            windSpeedMph = "${it.day.maxwind_mph} MPH",
                            windSpeedKph = "${it.day.maxwind_kph} KPH"
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