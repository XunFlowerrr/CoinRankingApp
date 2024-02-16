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

@HiltViewModel
class CoinViewModel @Inject constructor(
    pager: Pager<Int, CoinEntity>
) : ViewModel() {

    val coinPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toCoin() }
        }
        .cachedIn(viewModelScope)
}