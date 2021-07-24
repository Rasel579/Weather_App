package com.test_app.open_weater.models

import com.test_app.open_weater.models.rest.WeatherDTO
import retrofit2.Callback

interface Repository {
    fun getDataFromApi(cityName : String, callback: Callback<WeatherDTO>)
}