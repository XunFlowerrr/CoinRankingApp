package com.example.coinrankingapp.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.example.coinrankingapp.data.local.CoinDatabase
import com.example.coinrankingapp.data.local.CoinEntity
import com.example.coinrankingapp.data.remote.CoinApi
import com.example.coinrankingapp.data.remote.CoinRemoteMediator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides the coin database
     */
    @Provides
    @Singleton
    fun provideCoinDatabase(@ApplicationContext context: Context): CoinDatabase {
        return Room.databaseBuilder(
            context,
            CoinDatabase::class.java,
            "coins.db"
        ).build()
    }

    /**
     * Provides the coin api
     */
    @Provides
    @Singleton
    fun provideCoinApi(): CoinApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.coinranking.com/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return retrofit.create()
    }

    /**
     * Provides the coin pager
     */
    @Provides
    @Singleton
    fun provideCoinPager(coinDB: CoinDatabase, coinApi: CoinApi): Pager<Int, CoinEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            remoteMediator = CoinRemoteMediator(coinDb = coinDB, coinApi = coinApi),
            pagingSourceFactory = { coinDB.dao.pagingSource() }
        )
    }
}