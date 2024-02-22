package com.example.coinrankingapp.data.remote

import com.example.coinrankingapp.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * This interface represents the API for fetching coin data.
 * It uses Retrofit annotations to define the HTTP request.
 */
interface CoinApi {

    /**
     * This function sends a GET request to the /coins endpoint of the API.
     * It includes various optional query parameters for filtering and sorting the results.
     * The API key is included in the request headers.
     *
     * @param token The API key. By default, it is set to the API key defined in BuildConfig.
     * @param referenceCurrencyUuid The UUID of the reference currency.
     * @param timePeriod The time period for which to fetch coin data.
     * @param symbols The symbols of the coins to fetch.
     * @param uuids The UUIDs of the coins to fetch.
     * @param tiers The tiers of the coins to fetch.
     * @param tags The tags of the coins to fetch.
     * @param orderBy The field by which to order the results.
     * @param search A search term to filter the results.
     * @param scopeId The ID of the scope for the request.
     * @param scopeLimit The limit for the scope of the request.
     * @param orderDirection The direction in which to order the results.
     * @param limit The maximum number of results to return.
     * @param offset The number of results to skip before starting to fetch.
     * @return A Response object containing the results of the request.
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

    /**
     * This companion object contains constants related to the CoinApi.
     * Currently, it only contains the base URL for the API.
     */
    companion object {
        const val BASE_URL = "https://api.coinranking.com/v2/coins/"
    }
}