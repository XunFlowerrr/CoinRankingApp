package com.example.coinrankingapp.presentation

import android.content.Context
import android.content.Intent
import android.util.Log
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
 * CoinComponentClickable is a class that is used to build a CoinComponentClickable object.
 * @param context is a Context object.
 * @param coin is a Coin object.
 * @param intent is an Intent object.
 * @return a CoinComponentClickable object.
 * @see Coin
 */
class CoinComponentClickable(
    private val context: Context,
    private val coin: Coin,
    private val intent: Intent
) {

    /**
     * Build is a function that is used to build the CoinComponentClickable object.
     * @return a CoinComponentClickable object.
     */
    @Composable
    fun Build() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    Log.d("CoinComponentClickable", "Build: " + coin.coinrankingUrl)
                    context.startActivity(intent)
                },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                horizontalAlignment = Alignment.End, modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
            ) {

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
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(coin.iconUrl)
                    .decoderFactory(SvgDecoder.Factory()).build(),
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .width(50.dp)
                    .height(50.dp)
            )
        }
    }
}
