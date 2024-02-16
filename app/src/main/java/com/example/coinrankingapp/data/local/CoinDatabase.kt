package com.example.coinrankingapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [CoinEntity::class],
    version = 1
)
/**
 * The database for the app
 */
abstract class CoinDatabase : RoomDatabase() {

    abstract val dao: CoinDto
}