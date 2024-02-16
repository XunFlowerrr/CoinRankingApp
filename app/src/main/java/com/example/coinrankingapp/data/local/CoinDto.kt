package com.example.coinrankingapp.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

/**
 * The Data Transfer Object for the [CoinEntity] class.
 */
@Dao
interface CoinDto {


    /**
     * Insert or update a list of [CoinEntity] into the database.
     */
    @Upsert
    suspend fun upsertAll(coins: List<CoinEntity>)

    /**
     * Get a [PagingSource] of [CoinEntity] from the database.
     */
    @Query("SELECT * FROM coinentity")
    fun pagingSource(): PagingSource<Int, CoinEntity>

    /**
     * Clear all [CoinEntity] from the database.
     */
    @Query("DELETE FROM coinentity")
    suspend fun clearAll()
}