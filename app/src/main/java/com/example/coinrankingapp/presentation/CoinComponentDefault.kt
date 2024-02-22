package com.example.coinrankingapp.presentation

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.coinrankingapp.R
import com.example.coinrankingapp.domain.Coin

/**
 * This class represents a default component for a Coin in the application.
 * It is responsible for building the UI representation of a Coin.
 *
 * @property context The context in which this component is being used.
 * @property coin The Coin object that this component represents.
 * @property intent The intent that will be used for navigation.
 */
class CoinComponentDefault(
    private var context: Context,
    private var coin: Coin,
    private var intent: Intent
) {


    /**
     * This function builds the UI for the Coin component.
     * It creates a row layout with an image and two text views for the coin's name and symbol.
     */
    @Composable
    fun Build() {
        val showImageShimmer = remember { mutableStateOf(true) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(shimmerBrush(showImageShimmer.value, targetValue = 1300f))
        ) {


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
                },
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                if (!showImageShimmer.value) {
                    Text(
                        text = coin.name!!,
                        fontSize = 16.sp,
                        fontFamily = FontFamily(Font(R.font.robotobold)),
                        maxLines = 1
                    )
                    Text(
                        text = coin.symbol!!,
                        fontSize = 14.sp,
                        color = Color(0xFF999999),
                        fontFamily = FontFamily(Font(R.font.robotoregular)),
                        modifier = Modifier.padding(top = 12.dp)
                    )

                }

            }
        }
    }
}

