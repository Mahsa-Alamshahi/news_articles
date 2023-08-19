package org.newsapi.newsarticles.presentation.search_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.newsapi.newsarticles.R
import org.newsapi.newsarticles.common.Constants.BULLET
import org.newsapi.newsarticles.common.navigation.Screen
import org.newsapi.newsarticles.common.navigation.toJson
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity
import org.newsapi.newsarticles.presentation.favorites_screen.FavoritesViewModel
import org.newsapi.newsarticles.presentation.search_screen.SearchViewModel

@OptIn(ExperimentalGlideComposeApi::class, ExperimentalFoundationApi::class)
@Composable
fun NewsArticleListItem(
    navController: NavController,
    article: NewsArticleEntity,
    viewModel: SearchViewModel,
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {


    val configuration = LocalConfiguration.current
    val heightInDp = configuration.screenHeightDp.dp


    var isFavoriteIconClicked by remember { mutableStateOf(false) }

    val state: MutableState<Boolean> =
        remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            state.value = viewModel.isArticleExistedInFavorites(article.title!!)
        }
    }


    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
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
                    contentDescription = viewModel.checkNullableResponseField(article.name.toString()),
                    modifier = Modifier
                        .background(color = Color.Gray)
                        .fillMaxWidth()
                        .height(heightInDp / 4)
                        .clickable {
                            val articleString = article.toJson()
                            navController.navigate(
                                Screen.NewsArticleDetails.passArticle(
                                    articleString
                                )
                            )
                        },
                ) {
                    it.error(R.drawable.news_placeholder).centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                }
            }


            Column(
                modifier = Modifier
                    .weight(.6f)
                    .padding(start = 8.dp, top = 2.dp, bottom = 6.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {

                isFavoriteIconClicked = state.value

                IconButton(modifier = Modifier
                    .size(34.dp)
                    .align(Alignment.End)
                    .padding(end = 16.dp), onClick = {
                    isFavoriteIconClicked = !isFavoriteIconClicked
                    if (isFavoriteIconClicked) {
                        viewModel.addNewsArticle(article)
                    } else {
                        viewModel.deleteNewsArticle(article)
                    }
                }) {
                    Image(
                        painter = painterResource(
                            id = if (isFavoriteIconClicked) {
                                R.drawable.baseline_favorite_24
                            } else {
                                R.drawable.baseline_favorite_border_24
                            }
                        ), contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.Red)
                    )
                }


                Text(
                    text = viewModel.checkNullableResponseField(article.name.toString()),
                    modifier = Modifier.padding(bottom = 4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )


                Text(
                    text = article.title!!, modifier = Modifier.padding(bottom = 4.dp),
                    maxLines = 3, style = MaterialTheme.typography.titleMedium, color = Color.Black
                )


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Text(
                        text = viewModel.checkNullableResponseField(article.author.toString()),
                        maxLines = 1,
                        modifier = Modifier
                            .weight(.5f)
                            .basicMarquee(),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray
                    )

                    Text(
                        text = " $BULLET ",
                    )
                    if (article.publishedAt != null) {
                        Text(
                            text = viewModel.checkNullableResponseField(article.publishedAt.toString()),
                            modifier = Modifier
                                .weight(.5f),
                            maxLines = 1,
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}

