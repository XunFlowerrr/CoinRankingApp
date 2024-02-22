package com.example.coinrankingapp.data.local
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert


/**
 * This interface represents the DAO (Data Access Object) for the Coin database.
 * It provides methods for performing database operations such as upserting, querying, and deleting.
 */
@Dao
interface CoinDto {

    /**
     * This function upserts (updates or inserts) a list of CoinEntity objects into the database.
     *
     * @param coins The list of CoinEntity objects to be upserted.
     */
    @Upsert
    suspend fun upsertAll(coins: List<CoinEntity>)

    /**
     * This function queries the database and returns a PagingSource of CoinEntity objects.
     * The PagingSource can be used to load data in pages.
     *
     * @return A PagingSource of CoinEntity objects.
     */
    @Query("SELECT * FROM coinentity")
    fun pagingSource(): PagingSource<Int, CoinEntity>

    /**
     * This function deletes all CoinEntity objects from the database.
     */
    @Query("DELETE FROM coinentity")
    suspend fun clearAll()
}