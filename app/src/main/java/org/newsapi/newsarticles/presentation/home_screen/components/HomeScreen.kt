package org.newsapi.newsarticles.presentation.home_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.newsapi.newsarticles.R
import org.newsapi.newsarticles.presentation.favorites_screen.FavoritesScreen
import org.newsapi.newsarticles.presentation.news_articles_screen.NewsArticlesScreen

@Composable
fun HomeScreen() {

    var tabIndex by remember {
        mutableStateOf(0)
    }
    val tabs = listOf(stringResource(R.string.news_articles), stringResource(R.string.favorites))

    Column(modifier = Modifier.fillMaxWidth()) {

        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = {
                    Text(title)
                }, selected = tabIndex == index, onClick = { tabIndex = index }, icon = {
                    when (index) {
                        0 -> Icon(
                            painter = painterResource(id = R.drawable.baseline_feed_24),
                            contentDescription = "News Articles Tab"
                        )

                        1 -> Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorites Tab"
                        )
                    }
                })
            }
        }
        when (tabIndex) {
            0 -> NewsArticlesScreen()
            1 -> FavoritesScreen()
        }
    }

}


@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}