package com.example.coinrankingapp.domain

data class Coin(
    val symbol: String? = null,
    val marketCap: String? = null,
    val color: String? = null,
    val change: String? = null,
    val btcPrice: String? = null,
    val listedAt: Int? = null,
    val uuid: String? = null,
//    val sparkline: List<String?>? = null,
    val jsonMember24hVolume: String? = null,
    val coinrankingUrl: String? = null,
    val price: String? = null,
    val name: String? = null,
    val rank: Int? = null,
    val iconUrl: String? = null
)

