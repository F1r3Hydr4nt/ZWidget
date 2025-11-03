package com.zwidget.app.widget

import android.content.Context
import com.zwidget.app.data.models.LocationTimeData
import com.zwidget.app.data.models.WidgetState
import com.zwidget.app.data.preferences.WidgetPreferences
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object WidgetStateManager {

    private var cachedState: WidgetState? = null

    fun getState(context: Context): WidgetState {
        return cachedState ?: loadDefaultState(context)
    }

    fun updateState(newState: WidgetState) {
        cachedState = newState
    }

    private fun loadDefaultState(context: Context): WidgetState {
        return runBlocking {
            val prefs = WidgetPreferences(context)
            val mainLocation = prefs.mainLocation.first()
            val mainTimeZone = prefs.mainTimeZone.first()
            val subLocations = prefs.subLocations.first()
            val subTimeZones = prefs.subTimeZones.first()

            val subLocationsList = subLocations.zip(subTimeZones).map { (name, tz) ->
                LocationTimeData(name, tz, false)
            }

            WidgetState(
                mainLocation = LocationTimeData(mainLocation, mainTimeZone, true),
                subLocations = subLocationsList,
                weatherData = null,
                bitcoinPrice = null,
                currencyRate = null,
                lastUpdate = 0L
            )
        }
    }
}
