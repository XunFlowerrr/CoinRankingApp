package com.example.coinrankingapp.presentation

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
 * CoinComponentDefault is a class that is used to build a CoinComponentDefault object.
 * @param context is a Context object.
 * @param coin is a Coin object.
 * @param intent is an Intent object.
 * @return a CoinComponentDefault object.
 * @see Coin
 */
class CoinComponentDefault(
    private var context: Context,
    private var coin: Coin,
    private var intent: Intent
) {

    /**
     * Build is a function that is used to build the CoinComponentDefault object.
     * @return a CoinComponentDefault object.
     */
    @Composable
    fun Build() {
        Row(
            modifier = Modifier.fillMaxWidth()
//            .clickable { context.startActivity(intent) }

        ) {

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(coin.iconUrl)
                    .decoderFactory(SvgDecoder.Factory()).build(),
                contentDescription = null,
                modifier = Modifier
                    .padding(16.dp)
                    .width(50.dp)
                    .height(50.dp)
            )

            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = coin.name!!,
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.robotobold))
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

