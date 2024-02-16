package com.example.coinrankingapp.data.remote

data class CoinResponse(
    val data: Data? = null,
    val status: String? = null
)

data class Stats(
    val total: Int? = null,
    val totalExchanges: Int? = null,
    val totalMarkets: Int? = null,
    val totalMarketCap: String? = null,
    val total24hVolume: String? = null,
    val totalCoins: Int? = null
)

data class CoinsItem(
    val symbol: String? = null,
    val marketCap: String? = null,
    val color: String? = null,
    val change: String? = null,
    val btcPrice: String? = null,
    val listedAt: Int? = null,
    val uuid: String? = null,
    val sparkline: List<String?>? = listOf(null),
    val jsonMember24hVolume: String? = null,
    val coinrankingUrl: String? = null,
    val price: String? = null,
    val name: String? = null,
    val rank: Int? = null,
    val iconUrl: String? = null
)

data class Data(
    val stats: Stats? = null,
    val coins: List<CoinsDto?>? = null
)