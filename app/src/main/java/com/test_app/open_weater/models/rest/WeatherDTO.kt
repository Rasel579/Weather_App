package com.test_app.open_weater.models.rest

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.test_app.open_weater.models.rest.dto.MainWeatherDTO
import com.test_app.open_weater.models.rest.dto.SunSetTimeDTO
import com.test_app.open_weater.models.rest.dto.WindDTO
import kotlinx.android.parcel.Parcelize


data class WeatherDTO(
    @SerializedName("name")
    val location : String,
    val main: MainWeatherDTO,
    @SerializedName("wind")
    val windSpeed : WindDTO,
    val visibility: String,
    @SerializedName("sys")
    val sunset: SunSetTimeDTO,
    val cod : String,
    val message: String)

