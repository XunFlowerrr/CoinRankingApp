package com.example.coinrankingapp.data.local
import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * This abstract class represents the database for the application.
 * It is annotated with @Database, indicating the entities that belong in the database and the version number.
 *
 * @property dao The data access object (DAO) that provides methods for performing database operations.
 */
@Database(
    entities = [CoinEntity::class],
    version = 1
)
abstract class CoinDatabase : RoomDatabase() {

    /**
     * This abstract property represents the DAO for the Coin database.
     * It should be implemented to provide an instance of CoinDto.
     */
    abstract val dao: CoinDto
}