package com.example.coinrankingapp.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.coinrankingapp.R
import com.example.coinrankingapp.domain.Coin

/**
 * CoinRankingViewer is a composable function that is used to display the CoinRankingViewer.
 * @param coins is a LazyPagingItems<Coin> object.
 * @return a CoinRankingViewer object.
 * @see Coin
 */

class CoinRankingViewer(var coins: LazyPagingItems<Coin>) {
    @Composable
    fun Build() {
        val context = LocalContext.current

        LaunchedEffect(key1 = coins.loadState)
        {
            if (coins.loadState.refresh is LoadState.Error) {
                Toast.makeText(
                    context,
                    "Error: " + (coins.loadState.refresh as LoadState.Error).error.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }


        Column(modifier = Modifier.fillMaxSize())
        {
            Text(
                text = "BTC",
                color = Color(0xFF000000),
                fontFamily = FontFamily(Font(R.font.robotobold)),
                fontSize = 20.sp,
                modifier = Modifier
                    .background(Color(0xFF78C5FF))
                    .padding(16.dp)
                    .fillMaxWidth()
            )

            LazyColumn {
                items(
                    count = coins.itemCount,
                    key = { index -> coins[index]?.rank?.toString() ?: "$index" }
                ) { index ->
                    val coin = coins[index]
                    if (coin != null) {
                        CoinComponent(coin, coins).unique(index, 5).Build()
                    }
                    Box(
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color(0xFFEEEEEE))
                    )
                }
            }

        }

    }


}