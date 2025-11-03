package com.zwidget.app.data.models

data class LocationTimeData(
    val locationName: String,
    val timeZoneId: String,
    val isMain: Boolean = false
)

data class WidgetState(
    val mainLocation: LocationTimeData? = null,
    val subLocations: List<LocationTimeData> = emptyList(),
    val weatherData: WeatherData? = null,
    val bitcoinPrice: Double? = null,
    val currencyRate: Double? = null,
    val lastUpdate: Long = 0L,
    val selectedSubLocationIndex: Int = -1
)
