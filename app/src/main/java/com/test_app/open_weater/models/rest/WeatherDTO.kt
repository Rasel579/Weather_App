package com.test_app.open_weater.models.rest

import com.google.gson.annotations.SerializedName
import com.test_app.open_weater.models.rest.dto.MainWeatherDTO
import com.test_app.open_weater.models.rest.dto.SunSetTimeDTO
import com.test_app.open_weater.models.rest.dto.WindDTO

data class WeatherDTO(
    @SerializedName("name")
    val location : String,
    val main: MainWeatherDTO,
    val windSpeed : WindDTO,
    val visibility: String,
    @SerializedName("sys")
    val sunset: SunSetTimeDTO)
