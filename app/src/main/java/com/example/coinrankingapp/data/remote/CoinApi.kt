package com.example.coinrankingapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import com.example.coinrankingapp.BuildConfig

interface CoinApi {

    /**
     * Get a list of coins from the CoinRanking API.
     */
    @GET("coins")
    suspend fun getCoins(
        @Header("x-access-token") token: String = BuildConfig.API_KEY,
        @Query("referenceCurrencyUuid") referenceCurrencyUuid: String? = null,
        @Query("timePeriod") timePeriod: String? = null,
        @Query("symbols") symbols: List<String>? = null,
        @Query("uuids") uuids: List<String>? = null,
        @Query("tiers") tiers: List<Int>? = null,
        @Query("tags") tags: List<String>? = null,
        @Query("orderBy") orderBy: String? = null,
        @Query("search") search: String? = null,
        @Query("scopeId") scopeId: String? = null,
        @Query("scopeLimit") scopeLimit: Int? = null,
        @Query("orderDirection") orderDirection: String? = null,
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): Response<CoinResponse>

    companion object {
        const val BASE_URL = "https://api.coinranking.com/v2/coins/"
    }
}