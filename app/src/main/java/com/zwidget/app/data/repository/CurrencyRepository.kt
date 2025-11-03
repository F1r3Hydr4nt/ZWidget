package com.zwidget.app.data.repository

import com.zwidget.app.data.api.CurrencyApi
import com.zwidget.app.data.models.CurrencyData

class CurrencyRepository(private val api: CurrencyApi) {

    suspend fun convertCurrency(
        from: String,
        to: String,
        amount: Double
    ): Result<CurrencyData> {
        return try {
            val response = api.getExchangeRates(from, to)
            val rate = response.rates[to] ?: return Result.failure(
                Exception("Rate not found for $to")
            )
            Result.success(
                CurrencyData(
                    fromCurrency = from,
                    toCurrency = to,
                    rate = rate,
                    convertedAmount = amount * rate
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getExchangeRate(from: String, to: String): Result<Double> {
        return try {
            val response = api.getExchangeRates(from, to)
            val rate = response.rates[to] ?: return Result.failure(
                Exception("Rate not found for $to")
            )
            Result.success(rate)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
