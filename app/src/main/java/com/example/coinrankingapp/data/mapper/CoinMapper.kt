package com.example.coinrankingapp.data.mapper

import com.example.coinrankingapp.data.local.CoinEntity
import com.example.coinrankingapp.data.remote.CoinsDto
import com.example.coinrankingapp.domain.Coin

/**
 * This function converts a CoinsDto object to a CoinEntity object.
 * It is used when storing data from the API into the local database.
 *
 * @receiver The CoinsDto object to be converted.
 * @return The converted CoinEntity object.
 */
fun CoinsDto.toCoinEntity(): CoinEntity {
    return CoinEntity(
        symbol = symbol,
        marketCap = marketCap,
        color = color,
        change = change,
        btcPrice = btcPrice,
        listedAt = listedAt,
        uuid = uuid,
        jsonMember24hVolume = jsonMember24hVolume,
        coinrankingUrl = coinrankingUrl,
        price = price,
        name = name,
        rank = rank,
        iconUrl = iconUrl
    )
}

/**
 * This function converts a CoinEntity object to a Coin object.
 * It is used when retrieving data from the local database to be used in the application.
 *
 * @receiver The CoinEntity object to be converted.
 * @return Coin The converted Coin object.
 */
fun CoinEntity.toCoin(): Coin {
    return Coin(
        symbol = symbol,
        marketCap = marketCap,
        color = color,
        change = change,
        btcPrice = btcPrice,
        listedAt = listedAt,
        uuid = uuid,
        jsonMember24hVolume = jsonMember24hVolume,
        coinrankingUrl = coinrankingUrl,
        price = price,
        name = name,
        rank = rank,
        iconUrl = iconUrl
    )
}