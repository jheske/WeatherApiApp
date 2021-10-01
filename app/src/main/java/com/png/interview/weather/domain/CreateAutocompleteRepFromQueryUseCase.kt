package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.model.AutocompleteViewData
import com.png.interview.weather.ui.model.AutocompleteViewRepresentation
import timber.log.Timber
import javax.inject.Inject

interface CreateAutocompleteRepFromQueryUseCase {
    suspend operator fun invoke(query: String): AutocompleteViewRepresentation
}

class DefaultCreateAutocompleteRepFromQueryUseCase @Inject constructor(
    private val getAutocompleteDataUseCase: GetAutocompleteDataUseCase
) : CreateAutocompleteRepFromQueryUseCase {
    override suspend fun invoke(query: String): AutocompleteViewRepresentation {
        Timber.d("getAutocompleteDataUseCase $query")
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