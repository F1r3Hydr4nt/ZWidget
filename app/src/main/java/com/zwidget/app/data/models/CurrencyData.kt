package com.zwidget.app.data.models

data class CurrencyData(
    val fromCurrency: String,
    val toCurrency: String,
    val rate: Double,
    val convertedAmount: Double
)

data class ExchangeRateResponse(
    val rates: Map<String, Double>,
    val base: String,
    val date: String
)
