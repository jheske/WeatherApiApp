package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.api.model.AutcompleteResponseItem
import com.png.interview.weather.ui.model.AutocompleteViewData
import com.png.interview.weather.ui.model.AutocompleteViewRepresentation
import com.png.interview.weather.ui.model.AvailableWeatherViewData
import com.png.interview.weather.ui.model.CurrentWeatherViewRepresentation
import java.text.DateFormat
import java.text.SimpleDateFormat
import javax.inject.Inject

interface CreateAutocompleteRepFromQueryUseCase {
    suspend operator fun invoke(query: String): AutocompleteViewRepresentation
}

class DefaultCreateAutocompleteRepFromQueryUseCase @Inject constructor(
    private val getAutocompleteDataUseCase: GetAutocompleteDataUseCase
) : CreateAutocompleteRepFromQueryUseCase {
    override suspend fun invoke(query: String): AutocompleteViewRepresentation {
        return when (val result = getAutocompleteDataUseCase(query)) {
            is NetworkResponse.Success -> {
                val list = arrayListOf<String>()
                result.body.forEach {
                    list.add(
                        "${it.name}, ${it.region}, ${it.country}"
                    )
                }
                AutocompleteViewRepresentation.AutocompleteViewRep(
                    AutocompleteViewData(list)
                )
            }
            else -> {
                AutocompleteViewRepresentation.Error
            }
        }
    }
}