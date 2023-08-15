package org.newsapi.newsarticles.presentation.article_details_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.jsoup.Jsoup
import org.newsapi.newsarticles.R
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsArticleDetailsScreen(navController: NavController, article: NewsArticleEntity?) {

    var author = "-"
    var name = "-"
    var publishedAt = "-"


    if (article?.author != null) {
        author = article.author!!
    }
    if (article?.name != null) {
        name = article.name!!
    }
    if (article?.publishedAt != null) {
        publishedAt = article.publishedAt!!
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = Color.Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(.4f)
        ) {

            GlideImage(
                model = article?.imageUrl,
                contentDescription = name,
                modifier = Modifier
                    .background(color = Color.Black)
                    .fillMaxWidth()
                    .align(Alignment.Center),
            ) {
                it.error(R.drawable.news33).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
            ) {


                Text(
                    text = article?.title!!,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                Text(
                    text = "$author  •  $publishedAt ",
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite Icon",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(32.dp)
                        .clickable {
//                            viewModel.addNewsArticle(article)
                        },
                    tint = Color.Red
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Favorite Icon",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .size(32.dp)
                        .clickable {
                                   navController.popBackStack()
                        },
                    tint = Color.Red
                )

            }

        }
        Column(
            modifier = Modifier
                .weight(.6f)
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start

            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu, contentDescription = null,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = name,
                        modifier = Modifier.padding(bottom = 16.dp).align(Alignment.CenterVertically),
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.Black
                    )
                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth().padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                    ),
                    border = BorderStroke(1.dp, Color.Gray),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    )
                ) {
                    Text(
                        text = parseHtml(article?.description!!),
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.DarkGray
                    )
                }
                Text(
                    text = parseHtml(article?.content!!),
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.DarkGray
                )
                Text(
                    text = article.url!!, modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.labelLarge, color = Color.Black
                )

            }


        }
    }
}


fun parseHtml(description: String): String {
    return Jsoup.parse(description).text()
}

