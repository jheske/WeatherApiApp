package com.png.interview.weather.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class AutoCompleteResponse : ArrayList<AutocompleteResponseItem>()