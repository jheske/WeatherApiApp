package com.png.interview.weather.di

import com.png.interview.weather.domain.*
import dagger.Binds
import dagger.Module

@Module
abstract class WeatherUseCaseModule {

    @Binds
    abstract fun bindsGetCurrentWeatherDataUseCase(usecase: DefaultGetCurrentWeatherDataUseCase): GetCurrentWeatherDataUseCase

    @Binds
    abstract fun bindsGetCurrentWeatherRepUseCase(usecase: DefaultCreateCurrentWeatherRepFromQueryUseCase): CreateCurrentWeatherRepFromQueryUseCase

    @Binds
    abstract fun bindsGetWeatherForecastDataUseCase(usecase: DefaultGetWeatherForecastDataUseCase): GetWeatherForecastDataUseCase

    @Binds
    abstract fun bindsGetWeatherForecastRepUseCase(usecase: DefaultCreateWeatherForecastRepFromQueryUseCase): CreateWeatherForecastRepFromQueryUseCase
}