package com.zwidget.app.util

object WeatherUtil {

    fun getWeatherEmojiFromDescription(description: String, code: String): String {
        val lowerDesc = description.lowercase()
        return when {
            // Thunderstorm
            lowerDesc.contains("thunder") || lowerDesc.contains("storm") -> "⛈️"
            // Rain
            lowerDesc.contains("rain") || lowerDesc.contains("shower") -> "🌧️"
            lowerDesc.contains("drizzle") -> "🌦️"
            // Snow
            lowerDesc.contains("snow") || lowerDesc.contains("sleet") -> "❄️"
            lowerDesc.contains("ice") || lowerDesc.contains("freezing") -> "🧊"
            // Atmosphere
            lowerDesc.contains("fog") || lowerDesc.contains("mist") || lowerDesc.contains("haze") -> "🌫️"
            // Clear
            lowerDesc.contains("clear") || lowerDesc.contains("sunny") -> "☀️"
            // Clouds
            lowerDesc.contains("cloud") || lowerDesc.contains("overcast") -> "☁️"
            lowerDesc.contains("partly") -> "⛅"
            // Wind
            lowerDesc.contains("wind") -> "💨"
            // Default
            else -> "🌤️"
        }
    }

    // Legacy method for backward compatibility
    fun getWeatherEmoji(weatherId: Int): String {
        return when (weatherId) {
            // Thunderstorm
            in 200..232 -> "⛈️"
            // Drizzle
            in 300..321 -> "🌦️"
            // Rain
            in 500..531 -> "🌧️"
            // Snow
            in 600..622 -> "❄️"
            // Atmosphere (fog, mist, etc.)
            in 701..781 -> "🌫️"
            // Clear
            800 -> "☀️"
            // Clouds
            in 801..804 -> "☁️"
            // Default
            else -> "🌤️"
        }
    }

    fun formatTemperature(temp: Double): String {
        return "${temp.toInt()}°C"
    }
}
