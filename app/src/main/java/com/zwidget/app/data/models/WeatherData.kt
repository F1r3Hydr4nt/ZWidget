package com.zwidget.app.data.models

data class WeatherData(
    val temperature: Double,
    val description: String,
    val emoji: String,
    val locationName: String,
    val humidity: Int? = null,
    val windSpeed: Double? = null
)

// Google Weather API response models
data class GoogleWeatherResponse(
    val currentConditions: List<CurrentCondition>
)

data class CurrentCondition(
    val temperature: Temperature,
    val humidity: Humidity,
    val windSpeed: WindSpeed,
    val weatherCondition: WeatherCondition,
    val observationTime: ObservationTime
)

data class Temperature(
    val value: Double,
    val units: String = "CELSIUS"
)

data class Humidity(
    val value: Int,
    val units: String = "PERCENT"
)

data class WindSpeed(
    val value: Double,
    val units: String = "METERS_PER_SECOND"
)

data class WeatherCondition(
    val code: String,
    val description: String
)

data class ObservationTime(
    val year: Int,
    val month: Int,
    val day: Int,
    val hours: Int,
    val minutes: Int,
    val seconds: Int
)
