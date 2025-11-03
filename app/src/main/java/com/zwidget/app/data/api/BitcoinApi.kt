package com.zwidget.app.data.api

import com.zwidget.app.data.models.CoinGeckoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BitcoinApi {
    @GET("api/v3/simple/price")
    suspend fun getBitcoinSVPrice(
        @Query("ids") ids: String = "bitcoin-cash-sv",
        @Query("vs_currencies") vsCurrencies: String = "usd"
    ): CoinGeckoResponse

    companion object {
        const val BASE_URL = "https://api.coingecko.com/"
    }
}
