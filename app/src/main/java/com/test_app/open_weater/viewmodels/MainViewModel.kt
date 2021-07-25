package com.test_app.open_weater.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test_app.open_weater.models.Repository
import com.test_app.open_weater.models.RepositoryImpl
import com.test_app.open_weater.models.rest.WeatherDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class MainViewModel(
    private val repository: Repository,
    private val liveData: MutableLiveData<AppState>
) : ViewModel(), CoroutineScope by MainScope() {
    private val tagCallbackError = "Callback_Error"
    fun getLiveData() = liveData
    fun getDataFromApi(cityName : String){
        liveData.value = AppState.Loading
        val callBack = object : Callback<WeatherDTO> {
            override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
                if (response.code() == 404){
                    liveData.postValue(AppState.Error(RuntimeException(response.message())))

                }
                if (response.isSuccessful && response.body() != null){
                        liveData.postValue(AppState.Success(response.body()))

                }
            }
            override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
                t.message?.let { Log.e(tagCallbackError, it) }
                liveData.postValue(AppState.Error(t))
                t.printStackTrace()
            }
        }
         launch(Dispatchers.IO) {
             repository.getDataFromApi(cityName, callBack)
         }
    }
}