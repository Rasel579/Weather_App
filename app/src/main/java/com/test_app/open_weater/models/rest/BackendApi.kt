package com.test_app.open_weater.models.rest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BackendApi {
  @GET("weather")
  fun getDataFromWeather(@Query("q") city : String,
                         @Query("units") units: String,
                         @Query("appid") apiKey : String
                         ) : Call<WeatherDTO>
}