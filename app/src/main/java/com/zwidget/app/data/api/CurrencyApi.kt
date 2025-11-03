package com.zwidget.app.data.api

import com.zwidget.app.data.models.ExchangeRateResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
    @GET("latest")
    suspend fun getExchangeRates(
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): ExchangeRateResponse

    companion object {
        // Using exchangerate-api.com as a free alternative to xe.com
        const val BASE_URL = "https://api.exchangerate-api.com/v4/"
    }
}
