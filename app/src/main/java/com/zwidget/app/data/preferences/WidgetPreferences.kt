package com.zwidget.app.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "widget_prefs")

class WidgetPreferences(private val context: Context) {

    private object PreferencesKeys {
        val MAIN_LOCATION = stringPreferencesKey("main_location")
        val MAIN_TIMEZONE = stringPreferencesKey("main_timezone")
        val SUB_LOCATIONS = stringSetPreferencesKey("sub_locations")
        val SUB_TIMEZONES = stringSetPreferencesKey("sub_timezones")
        val HOME_CURRENCY = stringPreferencesKey("home_currency")
        val LOCAL_CURRENCY = stringPreferencesKey("local_currency")
        val WEATHER_API_KEY = stringPreferencesKey("weather_api_key")
        val LAST_WEATHER_UPDATE = longPreferencesKey("last_weather_update")
        val LAST_BITCOIN_PRICE = doublePreferencesKey("last_bitcoin_price")
        val LAST_CURRENCY_RATE = doublePreferencesKey("last_currency_rate")
    }

    val mainLocation: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.MAIN_LOCATION] ?: "London"
    }

    val mainTimeZone: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.MAIN_TIMEZONE] ?: "Europe/London"
    }

    val subLocations: Flow<Set<String>> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.SUB_LOCATIONS] ?: setOf("New York", "Tokyo", "Sydney")
    }

    val subTimeZones: Flow<Set<String>> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.SUB_TIMEZONES] ?: setOf(
            "America/New_York",
            "Asia/Tokyo",
            "Australia/Sydney"
        )
    }

    val homeCurrency: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.HOME_CURRENCY] ?: "USD"
    }

    val localCurrency: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.LOCAL_CURRENCY] ?: "EUR"
    }

    val weatherApiKey: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[PreferencesKeys.WEATHER_API_KEY] ?: ""
    }

    suspend fun setMainLocation(location: String, timeZone: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.MAIN_LOCATION] = location
            preferences[PreferencesKeys.MAIN_TIMEZONE] = timeZone
        }
    }

    suspend fun setSubLocations(locations: Set<String>, timeZones: Set<String>) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.SUB_LOCATIONS] = locations
            preferences[PreferencesKeys.SUB_TIMEZONES] = timeZones
        }
    }

    suspend fun setCurrencies(home: String, local: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.HOME_CURRENCY] = home
            preferences[PreferencesKeys.LOCAL_CURRENCY] = local
        }
    }

    suspend fun setWeatherApiKey(apiKey: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.WEATHER_API_KEY] = apiKey
        }
    }

    suspend fun updateLastBitcoinPrice(price: Double) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.LAST_BITCOIN_PRICE] = price
        }
    }

    suspend fun updateLastCurrencyRate(rate: Double) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.LAST_CURRENCY_RATE] = rate
        }
    }
}
