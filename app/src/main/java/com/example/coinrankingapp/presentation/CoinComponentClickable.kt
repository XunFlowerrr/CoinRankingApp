package com.example.coinrankingapp.presentation

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.coinrankingapp.R
import com.example.coinrankingapp.domain.Coin

/**
 * This class represents a clickable component for a Coin in the application.
 * It is responsible for building the UI representation of a Coin and handling click events.
 *
 * @property context The context in which this component is being used.
 * @property coin The Coin object that this component represents.
 * @property intent The intent that will be used for navigation when the component is clicked.
 */
class CoinComponentClickable(
    private val context: Context,
    private val coin: Coin,
    private val intent: Intent
) {
    /**
     * This function builds the UI for the Coin component and handles click events.
     * It creates a row layout with a text view for the coin's name and URL, and an image view for the coin's icon.
     * When the component is clicked, it logs the coin's URL and starts an activity using the provided intent.
     */
    @Composable
    fun Build() {
        val showImageShimmer = remember { mutableStateOf(true) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    Log.d("CoinComponentClickable", "Build: ${coin.coinrankingUrl}")
                    context.startActivity(intent)
                }
                .background(shimmerBrush(showImageShimmer.value, targetValue = 1300f)),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                horizontalAlignment = Alignment.End, modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {

                if (!showImageShimmer.value) {
                    Text(
                        text = coin.name!!,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Right,
                        fontFamily = FontFamily(Font(R.font.robotobold))
                    )

                    coin.coinrankingUrl?.let {
                        Text(
                            text = it,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Right,
                            lineHeight = 15.sp,
                            color = Color(0xFF999999),
                            fontFamily = FontFamily(Font(R.font.robotoregular)),
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            }

            // Load and display the coin's icon from a URL
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(coin.iconUrl)
                    .decoderFactory(SvgDecoder.Factory()).build(),
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .width(50.dp)
                    .height(50.dp),
                onSuccess = {
                    showImageShimmer.value = false
                })
        }
    }
}

