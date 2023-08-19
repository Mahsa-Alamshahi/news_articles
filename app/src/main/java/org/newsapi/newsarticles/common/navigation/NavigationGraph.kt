package org.newsapi.newsarticles.common.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.newsapi.newsarticles.common.Constants.NEWS_ARTICLE_DETAILS_ARGUMENT_KEY
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity
import org.newsapi.newsarticles.presentation.article_details_screen.NewsArticleDetailsScreen
import org.newsapi.newsarticles.presentation.favorites_screen.FavoritesScreen
import org.newsapi.newsarticles.presentation.search_screen.NewsArticlesScreen

@Composable
fun NavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController, startDestination = Screen.NewsArticle.route) {
        newsArticleRoute(navController, paddingValues)
        favoriteRoute(navController, paddingValues)
        newsArticleDetailsRoute(navController)
    }
}


fun NavGraphBuilder.newsArticleRoute(navController: NavController, paddingValues: PaddingValues) {
    composable(
        route = Screen.NewsArticle.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(700)
            )
        },
    ) {
        NewsArticlesScreen(navController, paddingValues)
    }
}


fun NavGraphBuilder.favoriteRoute(navController: NavController, paddingValues: PaddingValues) {
    composable(
        route = Screen.Favotite.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(700)
            )
        },
    ) {
        FavoritesScreen(navController, paddingValues)
    }
}


fun NavGraphBuilder.newsArticleDetailsRoute(navController: NavController) {
    composable(
        route = Screen.NewsArticleDetails.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(700)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(700)
            )
        },
        arguments = listOf(navArgument(NEWS_ARTICLE_DETAILS_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->

        navBackStackEntry.arguments?.getString(NEWS_ARTICLE_DETAILS_ARGUMENT_KEY)
            ?.let { jsonString ->
                val article = jsonString.fromJson(NewsArticleEntity::class.java)
                NewsArticleDetailsScreen(navController, article)
            }
    }
}

