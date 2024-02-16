package com.example.coinrankingapp.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.LazyPagingItems
import com.example.coinrankingapp.domain.Coin

/**
 * CoinComponent is a class that is used to build a CoinComponent object.
 * @param coin is a Coin object.
 * @param coinList is a LazyPagingItems<Coin> object.
 * @return a CoinComponent object.
 * @see Coin
 */
class CoinComponent(private var coin: Coin, private var coinList: LazyPagingItems<Coin>) {

    private var index: Int = 0
    private var interval: Int = 0

    /**
     * unique is a function that is used to set the index and interval of the CoinComponent object.
     * @param index is an Int.
     * @param interval is an Int.
     * @return a CoinComponent object.
     */
    fun unique(index: Int, interval: Int): CoinComponent {
        this.index = index
        this.interval = interval
        return this
    }

    /**
     * Build is a function that is used to build the CoinComponent object.
     * @return a CoinComponent object.
     */
    @Composable
    fun Build() {
        val url: String = coin.coinrankingUrl ?: ""
        val context: Context = LocalContext.current
        val intent: Intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(url)) }

        if ((index + 1) % interval == 0 && index != 0) {
            CoinComponentClickable(context, coin, intent).Build()

        } else {
            CoinComponentDefault(context, coin, intent).Build()
        }
    }

}
