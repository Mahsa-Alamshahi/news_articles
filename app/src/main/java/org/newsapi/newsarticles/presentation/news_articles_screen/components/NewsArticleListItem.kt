package org.newsapi.newsarticles.presentation.news_articles_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.squareup.moshi.Moshi
import org.newsapi.newsarticles.R
import org.newsapi.newsarticles.common.Constants
import org.newsapi.newsarticles.common.Constants.NEWS_ARTICLE_DETAILS_ARGUMENT_KEY
import org.newsapi.newsarticles.common.navigation.Screen
import org.newsapi.newsarticles.common.navigation.toJson
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun NewsArticleListItem(navController: NavController, article: NewsArticleEntity) {

    var author = "-"
    var name = "-"
    var publishedAt = "-"

    val configuration = LocalConfiguration.current
    val widthInDp = configuration.screenWidthDp.dp
    val heightInDp = configuration.screenHeightDp.dp


    if (article.author != null) {
        author = article.author!!
    }
    if (article.name != null) {
        name = article.name!!
    }
    if (article.publishedAt != null) {
        publishedAt = article.publishedAt!!
    }

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
//                navController.currentBackStackEntry?.arguments?.putParcelable(NEWS_ARTICLE_DETAILS_ARGUMENT_KEY, article)
//                val moshi = Moshi.Builder().build()
//                val jsonAdapter = moshi.adapter(NewsArticleEntity::class.java).lenient()
//                val articleJson = jsonAdapter.toJson(article)

                val articleString = article.toJson()

                    navController.navigate(Screen.NewsArticleDetails.passArticle(articleString))
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(1.dp, Color.Gray),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Row(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(
                modifier = Modifier
                    .weight(.4f)
                    .padding(0.dp)
                    .clickable { },
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
                border = BorderStroke(1.dp, Color.Gray),
                shape = RoundedCornerShape(0.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 16.dp
                )
            ) {

                GlideImage(
                    model = article.imageUrl,
                    contentDescription = article.name,
                    modifier = Modifier
                        .background(color = Color.Gray)
                        .fillMaxWidth()
                        .height(heightInDp / 5),
                ) {
                    it.error(R.drawable.news33).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                }

            }


            Column(
                modifier = Modifier
                    .weight(.6f)
                    .padding(start = 8.dp, top = 6.dp, bottom = 6.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start

            ) {


                if (article.name != null) {
                    Text(
                        text = article.name!!,
                        modifier = Modifier.padding(bottom = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )
                }
                Text(
                    text = article.title!!, modifier = Modifier.padding(bottom = 4.dp),
//                        .basicMarquee(),
                    maxLines = 3, style = MaterialTheme.typography.titleMedium, color = Color.Black
                )



                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val bullet = "\u2022"

                    Text(
                        text = author,
                        maxLines = 1,
                        modifier = Modifier
                            .weight(.5f)
                            .basicMarquee(),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )

                    Text(
                        text = " $bullet ",
                    )
                    if (article.publishedAt != null) {
                        Text(
                            text = "${article.publishedAt!!}",
                            modifier = Modifier
                                .weight(.5f)
                                .basicMarquee(),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray
                        )
                    }
                }

            }
        }
    }

}


//@Preview
//@Composable
//fun PreviewNewsArticleListItem() {
//    NewsArticleListItem()
//}