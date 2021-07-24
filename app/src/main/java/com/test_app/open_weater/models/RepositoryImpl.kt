package com.test_app.open_weater.models

import com.test_app.open_weater.BuildConfig
import com.test_app.open_weater.models.rest.BackendRepo
import com.test_app.open_weater.models.rest.WeatherDTO
import retrofit2.Callback

class RepositoryImpl : Repository {
    override fun getDataFromApi(cityName: String, callback: Callback<WeatherDTO>) {
        BackendRepo.api.getDataFromWeather(cityName, BuildConfig.WEATHER_APIKEY, "metric").enqueue(callback)
    }
}