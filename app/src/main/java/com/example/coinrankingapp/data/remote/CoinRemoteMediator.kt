package com.example.coinrankingapp.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.coinrankingapp.data.local.CoinDatabase
import com.example.coinrankingapp.data.local.CoinEntity
import com.example.coinrankingapp.data.mapper.toCoinEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class CoinRemoteMediator(
    private val coinDb: CoinDatabase,
    private val coinApi: CoinApi,
) : RemoteMediator<Int, CoinEntity>() {

    /**
     * Load data from the network and store it in the database and perform paging.
     */
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CoinEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {

                LoadType.REFRESH -> 0

                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        0
                    } else {
                        lastItem.rank
                    }
                }
            }
            val coins = coinApi.getCoins(
                offset = loadKey,
                limit = state.config.pageSize
            )
            Log.d("Paging", "LoadKey : $loadKey")

            coinDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    coinDb.dao.clearAll()
                }
                val coinEntities =
                    coins.body()?.data?.coins?.map { it?.toCoinEntity() ?: CoinEntity() }
                coinDb.dao.upsertAll(coinEntities ?: emptyList())
            }

            MediatorResult.Success(
                endOfPaginationReached = coins.body()?.data?.coins?.isEmpty() ?: true
            )


        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}