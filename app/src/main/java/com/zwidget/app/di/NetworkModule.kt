package com.zwidget.app.di

import com.zwidget.app.data.api.BitcoinApi
import com.zwidget.app.data.api.CurrencyApi
import com.zwidget.app.data.api.WeatherApi
import com.zwidget.app.data.repository.BitcoinRepository
import com.zwidget.app.data.repository.CurrencyRepository
import com.zwidget.app.data.repository.WeatherRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {

    private fun createOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun createRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideWeatherApi(): WeatherApi {
        return createRetrofit(WeatherApi.BASE_URL).create(WeatherApi::class.java)
    }

    fun provideCurrencyApi(): CurrencyApi {
        return createRetrofit(CurrencyApi.BASE_URL).create(CurrencyApi::class.java)
    }

    fun provideBitcoinApi(): BitcoinApi {
        return createRetrofit(BitcoinApi.BASE_URL).create(BitcoinApi::class.java)
    }

    fun provideWeatherRepository(): WeatherRepository {
        return WeatherRepository(provideWeatherApi())
    }

    fun provideCurrencyRepository(): CurrencyRepository {
        return CurrencyRepository(provideCurrencyApi())
    }

    fun provideBitcoinRepository(): BitcoinRepository {
        return BitcoinRepository(provideBitcoinApi())
    }
}
