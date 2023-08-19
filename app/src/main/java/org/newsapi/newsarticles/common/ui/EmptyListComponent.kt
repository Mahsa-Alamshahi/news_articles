package org.newsapi.newsarticles.common.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun EmptyList(resourseId: Int){


    val configuration = LocalConfiguration.current
    val heightInDp = configuration.screenHeightDp.dp
    val widthInDp = configuration.screenWidthDp.dp


    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {


        AnimationLoader(resId =resourseId, width = widthInDp, height = heightInDp)

    }
}