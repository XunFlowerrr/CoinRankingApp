package com.example.coinrankingapp.data.mapper

import com.example.coinrankingapp.data.local.CoinEntity
import com.example.coinrankingapp.data.remote.CoinsDto
import com.example.coinrankingapp.domain.Coin

/**
 * Extension functions for converting between data transfer objects and domain models.
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
 * Extension functions for converting between data transfer objects and domain models.
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
//        sparkline = sparkline,
        jsonMember24hVolume = jsonMember24hVolume,
        coinrankingUrl = coinrankingUrl,
        price = price,
        name = name,
        rank = rank,
        iconUrl = iconUrl
    )
}