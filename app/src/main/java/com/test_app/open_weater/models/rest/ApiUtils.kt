package com.test_app.open_weater.models.rest

import com.test_app.open_weater.models.WEATHER_API_URL
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object ApiUtils {
    val baseUrl = WEATHER_API_URL
    private val duration = 2000L
    fun getOkHttpClientWithHeaders(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.apply {
          connectTimeout(duration, TimeUnit.MILLISECONDS)
            readTimeout(duration, TimeUnit.MILLISECONDS)
            writeTimeout(duration, TimeUnit.MILLISECONDS)
        }
        httpClient.addInterceptor{ chain ->
           val originReq = chain.request()
            val request = originReq.newBuilder()
                .method(originReq.method(), originReq.body())
                .build()
            chain.proceed(request)
        }
        return httpClient.build()
    }

}