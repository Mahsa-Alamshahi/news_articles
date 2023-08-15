package org.newsapi.newsarticles.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.squareup.moshi.Moshi
import org.newsapi.newsarticles.common.Constants.NEWS_ARTICLE_DETAILS_ARGUMENT_KEY
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity
import org.newsapi.newsarticles.presentation.article_details_screen.NewsArticleDetailsScreen
import org.newsapi.newsarticles.presentation.favorites_screen.FavoritesScreen
import org.newsapi.newsarticles.presentation.news_articles_screen.NewsArticlesScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.NewsArticle.route) {
        newsArticleRoute(navController)
        favoriteRoute(navController)
        newsArticleDetailsRoute(navController)
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



fun NavGraphBuilder.newsArticleDetailsRoute(navController: NavController) {
    composable(
        route = Screen.NewsArticleDetails.route,
        arguments = listOf(navArgument(NEWS_ARTICLE_DETAILS_ARGUMENT_KEY) {
            type = NavType.StringType
        })
//        arguments = listOf(navArgument(name = NEWS_ARTICLE_DETAILS_ARGUMENT_KEY) {
//            type= NewsArticleDetailsNavArgs()
//            nullable = false
//        })
    ) { navBackStackEntry ->

//        val article = navController.previousBackStackEntry?.arguments?.getParcelable<NewsArticleEntity>(NEWS_ARTICLE_DETAILS_ARGUMENT_KEY)
//        NewsArticleDetailsScreen(navController, article)
//        val article = navBackStackEntry.arguments?.getString(NEWS_ARTICLE_DETAILS_ARGUMENT_KEY)
//            ?.let { Gson().fromJson(it, NewsArticleEntity::class.java) }

//        val articleJson =  navBackStackEntry.arguments?.getString(NEWS_ARTICLE_DETAILS_ARGUMENT_KEY)
//        val moshi = Moshi.Builder().build()
//        val jsonAdapter = moshi.adapter(NewsArticleEntity::class.java).lenient()
//        val articleObject = jsonAdapter.fromJson(articleJson)

        navBackStackEntry.arguments?.getString(NEWS_ARTICLE_DETAILS_ARGUMENT_KEY)?.let { jsonString ->
            val article = jsonString.fromJson(NewsArticleEntity::class.java)

            NewsArticleDetailsScreen(navController, article)
        }
    }
}

