package com.test_app.open_weater.viewmodels

import com.test_app.open_weater.models.rest.WeatherDTO

sealed class AppState {
  object Loading: AppState()
  data class Success(val data : WeatherDTO?): AppState()
    data class Error(val error: Throwable) : AppState()
}
