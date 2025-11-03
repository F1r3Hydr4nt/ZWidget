package com.zwidget.app.data.repository

import com.zwidget.app.data.api.BitcoinApi
import com.zwidget.app.data.models.BitcoinData

class BitcoinRepository(private val api: BitcoinApi) {

    suspend fun getBitcoinSVPrice(): Result<BitcoinData> {
        return try {
            val response = api.getBitcoinSVPrice()
            Result.success(
                BitcoinData(
                    price = response.bitcoin_cash_sv.usd,
                    currency = "USD"
                )
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
