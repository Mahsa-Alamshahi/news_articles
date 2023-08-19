package org.newsapi.newsarticles.presentation.article_details_screen

import android.content.res.ColorStateList
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.widget.TextView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.util.LinkifyCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.newsapi.newsarticles.R
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun NewsArticleDetailsScreen(
    navController: NavController,
    article: NewsArticleEntity?,
    viewModel: ArticleDetailsViewModel = hiltViewModel()
) {


    val states = arrayOf(
        intArrayOf(android.R.attr.state_enabled),
        intArrayOf(-android.R.attr.state_enabled),
        intArrayOf(android.R.attr.state_pressed)
    )


    val colors = intArrayOf(
        Color.Black.toArgb(), Color.Red.toArgb(), Color.Blue.toArgb()
    )

    var isInFavoriteList = viewModel.isExist.observeAsState(false)
    var isFavoriteIconClicked by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        viewModel.isArticleExistedInFavorites(article?.title!!)
    }

    val mContext = LocalContext.current
    val mCustomLinkifyText = remember { TextView(mContext) }


        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {

            GlideImage(
                model = article?.imageUrl,
                contentDescription = viewModel.checkNullableResponseField(article?.name),
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
            ) {
                it.error(R.drawable.news_placeholder).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL)
            }
        }

        Box(modifier = Modifier.fillMaxSize()){




            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart)
                    .background(color = Color.Gray.copy(alpha = .6f))
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start

            ) {

                Text(
                    text = article?.title!!,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                Text(
                    text = "${viewModel.checkNullableResponseField(article.author.toString())}  •  ${
                        viewModel.checkNullableResponseField(
                            article.publishedAt.toString()
                        )
                    } ",
                    modifier = Modifier.padding(8.dp),
                    maxLines= 1,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White
                )

                Divider(Modifier.fillMaxWidth().height(1.dp).background(Color.DarkGray))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_newspaper_24),
                        contentDescription = null,
                        modifier = Modifier.padding(8.dp),
                        tint = Color.White
                    )


                    Text(
                        text = viewModel.checkNullableResponseField(article.name.toString()),
                        modifier = Modifier
                            .padding(8.dp).weight(1f)
                            .align(Alignment.CenterVertically),
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )


                }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
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
                        text = viewModel.parseHtml(article.description!!.toString()),
                        modifier = Modifier.padding(8.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.Black
                    )
                }

                Text(
                    text = viewModel.parseHtml(article.content!!.toString()),
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )

                AndroidView(
                    factory = { mCustomLinkifyText },
                    modifier = Modifier.padding(bottom = 8.dp, start = 8.dp, end = 8.dp, top = 16.dp)
                ) { textView ->
                    textView.text = article.url
                    textView.textSize = 14F
                    textView.setTextColor(Color.Blue.toArgb())
                    textView.setTextColor(ColorStateList(states, colors))
                    LinkifyCompat.addLinks(textView, Linkify.ALL)
                    textView.movementMethod = LinkMovementMethod.getInstance()
                }

                isFavoriteIconClicked = isInFavoriteList.value
                IconButton(modifier = Modifier.align(Alignment.End)
                    .size(56.dp).padding(16.dp), onClick = {
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
                        colorFilter = ColorFilter.tint(Color(0xFF660101))
                    )
                }
            }
        }

}



