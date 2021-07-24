package com.test_app.open_weater.models

import com.test_app.open_weater.BuildConfig
import com.test_app.open_weater.models.rest.BackendRepo
import com.test_app.open_weater.models.rest.WeatherDTO
import retrofit2.Callback

class RepositoryImpl : Repository {
    private val units = "metric"
    override fun getDataFromApi(cityName: String, callback: Callback<WeatherDTO>) {
        BackendRepo.api.getDataFromWeather(cityName, units, BuildConfig.WEATHER_APIKEY).enqueue(callback)
    }
}