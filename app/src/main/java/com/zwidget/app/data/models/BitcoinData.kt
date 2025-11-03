package com.zwidget.app.data.models

data class BitcoinData(
    val price: Double,
    val currency: String = "USD"
)

data class CoinGeckoResponse(
    val bitcoin_cash_sv: BitcoinSVPrice
)

data class BitcoinSVPrice(
    val usd: Double
)
