package com.zwidget.app.data.repository

import com.zwidget.app.data.api.WeatherApi
import com.zwidget.app.data.models.WeatherData
import com.zwidget.app.util.WeatherUtil

class WeatherRepository(private val api: WeatherApi) {

    suspend fun getWeatherByCoordinates(
        latitude: Double,
        longitude: Double,
        locationName: String,
        apiKey: String
    ): Result<WeatherData> {
        return try {
            val response = api.getCurrentConditions(apiKey, latitude, longitude)
            val currentCondition = response.currentConditions.firstOrNull()
                ?: return Result.failure(Exception("No current conditions available"))

            Result.success(
                WeatherData(
                    temperature = currentCondition.temperature.value,
                    description = currentCondition.weatherCondition.description,
                    emoji = WeatherUtil.getWeatherEmojiFromDescription(
                        currentCondition.weatherCondition.description,
                        currentCondition.weatherCondition.code
                    ),
                    locationName = locationName,
                    humidity = currentCondition.humidity.value,
                    windSpeed = currentCondition.windSpeed.value
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
