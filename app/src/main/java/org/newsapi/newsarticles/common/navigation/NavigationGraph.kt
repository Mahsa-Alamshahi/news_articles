package org.newsapi.newsarticles.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.newsapi.newsarticles.presentation.favorites_screen.FavoritesScreen
import org.newsapi.newsarticles.presentation.news_articles_screen.NewsArticlesScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.NewsArticle.route) {
        newsArticleRoute(navController)
        favoriteRoute(navController)
    }
}


fun NavGraphBuilder.newsArticleRoute(navController: NavController) {
    composable(
        route = Screen.NewsArticle.route,
    ) {
        NewsArticlesScreen(navController)
    }
}

fun NavGraphBuilder.favoriteRoute(navController: NavController) {
    composable(
        route = Screen.Favotite.route,
    ) {
        FavoritesScreen(navController)
    }
}