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

/**
 * This class is a RemoteMediator for the CoinEntity data.
 * It handles the loading of data from the API and the local database.
 *
 * @property coinDb The local database for the CoinEntity data.
 * @property coinApi The API for fetching the CoinEntity data.
 */
@OptIn(ExperimentalPagingApi::class)
class CoinRemoteMediator(
    private val coinDb: CoinDatabase,
    private val coinApi: CoinApi,
) : RemoteMediator<Int, CoinEntity>() {

    /**
     * This function loads CoinEntity data from the API or the local database.
     * It handles the different types of loads (refresh, prepend, append) and manages the pagination.
     *
     * @param loadType The type of load (refresh, prepend, append).
     * @param state The current state of the paging.
     * @return A MediatorResult indicating the result of the load operation.
     */
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CoinEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {

                // For a refresh load, we start from the first page.
                LoadType.REFRESH -> 0

                // For a prepend load, we have reached the start of the data, so we return success.
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                // For an append load, we get the last item and use its rank as the next page number.
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        0
                    } else {
                        lastItem.rank
                    }
                }
            }
            // Fetch the data from the API.
            val coins = coinApi.getCoins(
                offset = loadKey,
                limit = state.config.pageSize
            )
            Log.d("Paging", "LoadKey : $loadKey")
            Log.d("Fetch", "Fetching :${coins.body()}")
            // Store the fetched data in the local database.
            coinDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    coinDb.dao.clearAll()
                }
                val coinEntities =
                    coins.body()?.data?.coins?.map { it?.toCoinEntity() ?: CoinEntity() }
                coinDb.dao.upsertAll(coinEntities ?: emptyList())
            }

            // Return success if there are no more pages to load.
            MediatorResult.Success(
                endOfPaginationReached = coins.body()?.data?.coins?.isEmpty() ?: true
            )


        } catch (e: IOException) {
            // Return an error if there was an IO exception.
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            // Return an error if there was an HTTP exception.
            MediatorResult.Error(e)
        }
    }
}