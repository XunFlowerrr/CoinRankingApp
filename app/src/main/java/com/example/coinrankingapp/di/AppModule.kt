package com.example.coinrankingapp.di
import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room.databaseBuilder
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

/**
 * This object is a Dagger module that provides dependencies for the application.
 * It is installed in the SingletonComponent, which means that the provided dependencies are singletons.
 */
@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * This function provides the CoinDatabase singleton.
     * It uses Room to create the database.
     *
     * @param context The application context.
     * @return The CoinDatabase singleton.
     */
    @Provides
    @Singleton
    fun provideCoinDatabase(@ApplicationContext context: Context): CoinDatabase {
        return databaseBuilder(
            context,
            CoinDatabase::class.java,
            "coins.db"
        ).build()
    }

    /**
     * This function provides the CoinApi singleton.
     * It uses Retrofit to create the API.
     *
     * @return The CoinApi singleton.
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
     * This function provides the Pager singleton for CoinEntity objects.
     * It uses the CoinDatabase and CoinApi singletons to create the Pager.
     *
     * @param coinDB The CoinDatabase singleton.
     * @param coinApi The CoinApi singleton.
     * @return The Pager singleton for CoinEntity objects.
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