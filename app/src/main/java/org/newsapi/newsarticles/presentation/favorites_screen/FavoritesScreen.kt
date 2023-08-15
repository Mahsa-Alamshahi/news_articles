package org.newsapi.newsarticles.presentation.favorites_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.newsapi.newsarticles.presentation.news_articles_screen.components.ErrorView
import org.newsapi.newsarticles.presentation.news_articles_screen.components.LoadingView
import org.newsapi.newsarticles.presentation.news_articles_screen.components.NewsArticleListItem

@Composable
fun FavoritesScreen(navController: NavController, viewModel: FavoritesViewModel = hiltViewModel()) {


    val favoriteListState = viewModel.newsListState.value

    Column(modifier = Modifier
        .fillMaxSize()) {


        if (favoriteListState.isLoading) {
            LoadingView()
        } else if (favoriteListState.error.isNotBlank()) {
            ErrorView()
        } else if (favoriteListState.articles.isEmpty()) {
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(4.dp)
            ) {
                items(items = favoriteListState.articles) { article ->
                    FavoriteListItem(navController = navController, article = article)
                }
            }

        }

    }

}


@Preview
@Composable
fun PreviewFavoriteScreen(){
    val navController = rememberNavController()
    FavoritesScreen(navController = navController)
}