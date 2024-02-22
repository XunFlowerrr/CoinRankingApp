package com.example.coinrankingapp
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.coinrankingapp.presentation.CoinRankingViewer
import com.example.coinrankingapp.ui.theme.CoinRankingAppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * This is the main activity of the application.
 * It is annotated with @AndroidEntryPoint, which means that Hilt will provide its dependencies.
 *
 * The activity uses Jetpack Compose to define its UI. The UI consists of a CoinRankingViewer,
 * which displays a list of coins fetched from the CoinViewModel.
 *
 * @see ComponentActivity
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            // Set the theme of the application.
            CoinRankingAppTheme {

                // Create a surface that fills the maximum size of the parent layout.
                Surface(
                    modifier = Modifier.fillMaxSize()

                ) {
                    // Get the CoinViewModel from Hilt.
                    val viewModel = hiltViewModel<CoinViewModel>()
                    // Collect the coin data from the ViewModel as a LazyPagingItems object.
                    val coins = viewModel.coinPagingFlow.collectAsLazyPagingItems()
                    // Display the coins in a CoinRankingViewer.
                    CoinRankingViewer(coins = coins)
                }
            }
        }
    }
}