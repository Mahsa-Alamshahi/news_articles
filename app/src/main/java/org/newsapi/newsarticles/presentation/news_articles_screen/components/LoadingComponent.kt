package org.newsapi.newsarticles.presentation.news_articles_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieAnimatable
import com.airbnb.lottie.compose.rememberLottieComposition
import org.newsapi.newsarticles.R

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        val clipSpecs = LottieClipSpec.Progress(0.2f, 0.5f)
//
//        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_anim))


        val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading_anim))
        val shouldReverse = remember { mutableStateOf(false) }
        val anim = rememberLottieAnimatable()


        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
        LottieAnimation(
            composition,
//        anim.value,
            progress = { progress },
            modifier = Modifier
                .size(100.dp, 100.dp)
                .wrapContentWidth(align = Alignment.CenterHorizontally),
//            enableMergePaths = remember { enableMergePaths },
            alignment = Alignment.Center
        )

    }
}