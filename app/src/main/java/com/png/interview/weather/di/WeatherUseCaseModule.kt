package com.png.interview.weather.di

import com.png.interview.weather.domain.*
import dagger.Binds
import dagger.Module

@Module
abstract class WeatherUseCaseModule {

    // Get Current Weather bindings
    @Binds
    abstract fun bindsGetCurrentWeatherDataUseCase(usecase: DefaultGetCurrentWeatherDataUseCase): GetCurrentWeatherDataUseCase

    @Binds
    abstract fun bindsGetCurrentWeatherRepUseCase(usecase: DefaultCreateCurrentWeatherRepFromQueryUseCase): CreateCurrentWeatherRepFromQueryUseCase

    // Get Autocomplete bindings
    @Binds
    abstract fun bindsGetAutocompleteDataUseCase(usecase: DefaultGetAutocompleteDataUseCase): GetAutocompleteDataUseCase

    @Binds
    abstract fun bindsGetAutocompleteRepUseCase(usecase: DefaultCreateAutocompleteRepFromQueryUseCase): CreateAutocompleteRepFromQueryUseCase

    // Get Weather Forecast bindings
    @Binds
    abstract fun bindsGetWeatherForecastDataUseCase(usecase: DefaultGetWeatherForecastDataUseCase): GetWeatherForecastDataUseCase

    @Binds
    abstract fun bindsGetWeatherForecastRepUseCase(usecase: DefaultCreateWeatherForecastRepFromQueryUseCase): CreateWeatherForecastRepFromQueryUseCase
}