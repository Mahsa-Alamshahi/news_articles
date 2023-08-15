package org.newsapi.newsarticles.presentation.favorites_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.newsapi.newsarticles.R
import org.newsapi.newsarticles.common.navigation.Screen
import org.newsapi.newsarticles.common.navigation.toJson
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity
import org.newsapi.newsarticles.presentation.news_articles_screen.SearchViewModel


@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun FavoriteListItem(
    navController: NavController,
    article: NewsArticleEntity,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val grayScaleMatrix = ColorMatrix(
        floatArrayOf(
            0.33f, 0.33f, 0.33f, 0f, 0f,
            0.33f, 0.33f, 0.33f, 0f, 0f,
            0.33f, 0.33f, 0.33f, 0f, 0f,
            0f, 0f, 0f, 1f, 0f
        )
    )

    val configuration = LocalConfiguration.current
    val widthInDp = configuration.screenWidthDp.dp
    val heightInDp = configuration.screenHeightDp.dp



    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                val articleString = article.toJson()
                navController.navigate(Screen.NewsArticleDetails.passArticle(articleString))
            }
    ) {


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .clickable {
                    val articleString = article.toJson()
                    navController.navigate(Screen.NewsArticleDetails.passArticle(articleString))
                },
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            border = BorderStroke(1.dp, Color.Gray),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 16.dp
            )
        ) {

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {


                Box(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth(),
                ) {


                    GlideImage(
                        model = article.imageUrl,
                        contentDescription = article.name,
                        modifier = Modifier
                            .background(color = Color.Gray)
                            .fillMaxWidth()
                            .size(height = heightInDp / 5, width = widthInDp),
                        alpha = .6f,
                        colorFilter = ColorFilter.colorMatrix(grayScaleMatrix)
                    ) {
                        it.error(R.drawable.news33).centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    }

                    Text(
                        text = article.title!!,
                        maxLines = 1,
                        modifier = Modifier
                            .padding(8.dp)
                            .basicMarquee()
                            .align(Alignment.BottomStart),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )


                }

                Text(
                    text = article.description!!,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        }
    }
}