package org.newsapi.newsarticles.presentation.news_articles_screen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.newsapi.newsarticles.R
import org.newsapi.newsarticles.data.data_source.remote.dto.ArticleDto
import org.newsapi.newsarticles.data.data_source.remote.dto.Source

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsArticleListItem(articleDto: ArticleDto) {


    val configuration = LocalConfiguration.current
    val widthInDp = configuration.screenWidthDp.dp
    val heightInDp = configuration.screenHeightDp.dp



    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
    ) {


        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .clickable { },
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
                        model = articleDto.urlToImage,
                        contentDescription = articleDto.source.name,
                        modifier = Modifier
                            .background(color = Color.Gray)
                            .fillMaxWidth()
                            .size(height = heightInDp / 4, width = widthInDp),
                    ) {
                        it.error(R.drawable.news33).centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                    }

                    Text(
                        text = articleDto.title,
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.BottomStart),
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White
                    )


                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite Icon",
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .size(32.dp),
                            tint = Color.Red
                        )
                    }


                }

                Text(
                    text = articleDto.description,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )


                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.End
                ) {

                    if (articleDto.author != null) {
                        Text(
                            text = articleDto.author,
                            modifier = Modifier.padding(1.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray
                        )
                    }
                    if (articleDto.source.name != null) {
                        Text(
                            text = articleDto.source.name,
                            modifier = Modifier.padding(1.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray
                        )
                    }
                    if (articleDto.publishedAt != null) {
                        Text(
                            text = articleDto.publishedAt,
                            modifier = Modifier.padding(1.dp),
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.Gray
                        )
                    }


                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewNewsItem() {
    var articleDto = ArticleDto(
        author = "Mahsa",
        title = "Test",
        content = "Every thing is Ok.",
        description = "sgdhdghdhnd",
        publishedAt = "22023-01-01",
        source = Source(id = "1", name = "testing"),
        url = "",
        urlToImage = ""
    )

    NewsArticleListItem(articleDto = articleDto)
}