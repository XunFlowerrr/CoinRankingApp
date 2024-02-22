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
 * This class represents a component for a Coin in the application.
 * It is responsible for building the UI representation of a Coin based on its position in the list.
 *
 * @property coin The Coin object that this component represents.
 * @property coinList The list of Coin objects.
 */
class CoinComponent(private var coin: Coin, private var coinList: LazyPagingItems<Coin>) {

    private var index: Int = 0
    private var interval: Int = 0

    /**
     * This function sets the index and interval for the Coin component.
     * It is used to determine whether to display a clickable or default component.
     *
     * @param index The position of the Coin in the list.
     * @param interval The interval at which to display a clickable component.
     * @return The CoinComponent object.
     */
    fun unique(index: Int, interval: Int): CoinComponent {
        this.index = index
        this.interval = interval
        return this
    }

    /**
     * This function builds the UI for the Coin component.
     * It creates either a clickable or default component based on the Coin's position in the list.
     */
    @Composable
    fun Build() {
        val url: String = coin.coinrankingUrl ?: ""
        val context: Context = LocalContext.current
        val intent: Intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(url)) }

        // If the Coin's position in the list is a multiple of the interval (and not the first position),
        // create a clickable component. Otherwise, create a default component.
        if ((index + 1) % interval == 0 && index != 0) {
            CoinComponentClickable(context, coin, intent).Build()

        } else {
            CoinComponentDefault(context, coin, intent).Build()
        }
    }
}