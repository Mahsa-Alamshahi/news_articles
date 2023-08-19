package org.newsapi.newsarticles.presentation.favorites_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.newsapi.newsarticles.R
import org.newsapi.newsarticles.common.navigation.Screen
import org.newsapi.newsarticles.common.navigation.toJson
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FavoriteListItem(
    navController: NavController, article: NewsArticleEntity
) {


    val configuration = LocalConfiguration.current
    val widthInDp = configuration.screenWidthDp.dp
    val heightInDp = configuration.screenHeightDp.dp



    Column(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .clickable {
            val articleString = article.toJson()
            navController.navigate(Screen.NewsArticleDetails.passArticle(articleString))
        }) {


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


                GlideImage(
                    model = article.imageUrl,
                    contentDescription = article.name,
                    modifier = Modifier
                        .background(color = Color.Gray)
                        .fillMaxWidth()
                        .size(height = heightInDp / 6, width = widthInDp),
                ) {
                    it.error(R.drawable.news_placeholder).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                }



                Text(
                    text = article.title!!,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
        }
    }
}