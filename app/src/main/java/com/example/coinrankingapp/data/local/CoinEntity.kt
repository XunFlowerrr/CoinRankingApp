package com.example.coinrankingapp.data.local
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This class represents a CoinEntity object.
 * It is used to store data from the API into the local database.
 */
@Entity
data class CoinEntity(
    val symbol: String? = null,
    val marketCap: String? = null,
    val color: String? = null,
    val change: String? = null,
    val btcPrice: String? = null,
    val listedAt: Int? = null,
    val uuid: String? = null,
    val jsonMember24hVolume: String? = null,
    val coinrankingUrl: String? = null,
    val price: String? = null,
    val name: String? = null,
    @PrimaryKey
    val rank: Int? = null,
    val iconUrl: String? = null
)