package com.zwidget.app.data.api

import com.zwidget.app.data.models.GoogleWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/currentConditions:lookup")
    suspend fun getCurrentConditions(
        @Query("key") apiKey: String,
        @Query("location.latitude") latitude: Double,
        @Query("location.longitude") longitude: Double
    ): GoogleWeatherResponse

    companion object {
        const val BASE_URL = "https://weather.googleapis.com/"
    }
}
