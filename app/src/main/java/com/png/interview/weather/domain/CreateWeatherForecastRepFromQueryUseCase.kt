package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.model.AvailableWeatherForecastViewData
import com.png.interview.weather.ui.model.AvailableWeatherViewData
import com.png.interview.weather.ui.model.CurrentWeatherViewRepresentation
import com.png.interview.weather.ui.model.WeatherForecastViewRepresentation
import javax.inject.Inject

interface CreateWeatherForecastRepFromQueryUseCase {
    suspend operator fun invoke(query: String): WeatherForecastViewRepresentation
}

class DefaultCreateWeatherForecastRepFromQueryUseCase @Inject constructor(
    private val getCurrentWeatherDataUseCase: GetCurrentWeatherDataUseCase
) : CreateWeatherForecastRepFromQueryUseCase {
    override suspend fun invoke(query: String): WeatherForecastViewRepresentation {
        return when (val result = getCurrentWeatherDataUseCase(query)) {
            is NetworkResponse.Success -> {
                WeatherForecastViewRepresentation.WeatherForecastViewRep(
                    AvailableWeatherForecastViewData(
                        name = result.body.location.name,
                        date = result.body.location.localtime,
                        minTemperature = "${result.body.current.temp_f} F",
                        maxTemperature = "${result.body.current.temp_f} F",
                        condition = result.body.current.condition.text,
                        windDirection = result.body.current.wind_dir,
                        windSpeed = "${result.body.current.gust_mph} MPH"
                    )
                )
            }
            else -> {
                WeatherForecastViewRepresentation.Error
            }
        }
    }
}