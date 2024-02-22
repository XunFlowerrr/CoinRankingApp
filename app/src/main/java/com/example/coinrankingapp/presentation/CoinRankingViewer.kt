package com.example.coinrankingapp.presentation
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
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
 * This composable function displays a list of coins and their rankings.
 * It handles the loading state of the coins and displays an error message if the loading fails.
 *
 * @param coins The list of Coin objects to be displayed.
 */
@Composable
fun CoinRankingViewer(coins: LazyPagingItems<Coin>) {

    // Get the current context
    val context = LocalContext.current

    // Display a toast message if the loading of coins fails
    LaunchedEffect(key1 = coins.loadState)
    {
        if (coins.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (coins.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    Column(modifier = Modifier.fillMaxSize())
    {
        Text(
            text = "BTC",
            color = isSystemInDarkTheme().let { if (it) Color(0xFFDDDDDD) else Color(0xFF000000) },
            fontFamily = FontFamily(Font(R.font.robotobold)),
            fontSize = 20.sp,
            modifier = Modifier
                .background(Color(0xFF78C5FF))
                .padding(16.dp)
                .fillMaxWidth()
        )


        // Create a lazy column for the list of coins
        LazyColumn {
            items(
                count = coins.itemCount,
                key = { index -> coins[index]?.rank?.toString() ?: "$index" }
            ) { index ->
                // Get the coin at the current index
                val coin = coins[index]
                // If the coin is not null, display it using the CoinComponent
                if (coin != null) {
                    CoinComponent(coin, coins).unique(index, 5).Build()
                }
                Box(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(isSystemInDarkTheme().let {
                            if (it) Color(0xFF222222) else Color(0xFFEEEEEE)
                        })
                )
            }
        }
    }
}
