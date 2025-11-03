package com.zwidget.app.widget

import android.content.Context
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.appwidget.updateAll
import com.zwidget.app.data.models.WidgetState
import com.zwidget.app.data.preferences.WidgetPreferences
import com.zwidget.app.di.NetworkModule
import com.zwidget.app.util.LocationUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

object WidgetUpdater {

    suspend fun updateWidget(context: Context) {
        withContext(Dispatchers.IO) {
            try {
                val prefs = WidgetPreferences(context)
                val weatherRepo = NetworkModule.provideWeatherRepository()
                val bitcoinRepo = NetworkModule.provideBitcoinRepository()
                val currencyRepo = NetworkModule.provideCurrencyRepository()

                // Fetch weather
                val apiKey = prefs.weatherApiKey.first()
                val mainLocation = prefs.mainLocation.first()
                val weatherResult = if (apiKey.isNotEmpty()) {
                    val coordinates = LocationUtil.getCoordinates(mainLocation)
                    if (coordinates != null) {
                        weatherRepo.getWeatherByCoordinates(
                            coordinates.latitude,
                            coordinates.longitude,
                            mainLocation,
                            apiKey
                        )
                    } else {
                        Result.failure(Exception("Location coordinates not found"))
                    }
                } else {
                    Result.failure(Exception("No API key"))
                }

                // Fetch Bitcoin price
                val bitcoinResult = bitcoinRepo.getBitcoinSVPrice()

                // Fetch currency rate
                val homeCurrency = prefs.homeCurrency.first()
                val localCurrency = prefs.localCurrency.first()
                val currencyResult = currencyRepo.getExchangeRate(homeCurrency, localCurrency)

                // Get current state
                val currentState = WidgetStateManager.getState(context)

                // Update state
                val newState = currentState.copy(
                    weatherData = weatherResult.getOrNull() ?: currentState.weatherData,
                    bitcoinPrice = bitcoinResult.getOrNull()?.price ?: currentState.bitcoinPrice,
                    currencyRate = currencyResult.getOrNull() ?: currentState.currencyRate,
                    lastUpdate = System.currentTimeMillis()
                )

                WidgetStateManager.updateState(newState)

                // Update all widget instances
                ZWidget().updateAll(context)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
