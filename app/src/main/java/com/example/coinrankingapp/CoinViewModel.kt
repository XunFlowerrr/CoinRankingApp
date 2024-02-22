package com.example.coinrankingapp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.coinrankingapp.data.local.CoinEntity
import com.example.coinrankingapp.data.mapper.toCoin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * This class is a ViewModel for the Coin data.
 * It is annotated with @HiltViewModel, which means that Hilt will provide its dependencies.
 *
 * @property pager A Pager object for CoinEntity objects. It is injected by Hilt.
 */
@HiltViewModel
class CoinViewModel @Inject constructor(
    private val pager: Pager<Int, CoinEntity>
) : ViewModel() {

    /**
     * This property is a Flow of PagingData of Coin objects.
     * It is created by mapping the CoinEntity objects from the pager's flow to Coin objects,
     * and caching the result in the ViewModel's scope.
     *
     * The Flow is exposed as a public property, so it can be collected by the UI.
     */
    val coinPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toCoin() }
        }
        .cachedIn(viewModelScope)
}