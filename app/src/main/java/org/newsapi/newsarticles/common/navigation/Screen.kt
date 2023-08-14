package org.newsapi.newsarticles.common.navigation

import androidx.compose.ui.res.stringResource
import org.newsapi.newsarticles.R

sealed class Screen(val route: String) {

    object NewsArticle: Screen(route = "News Articles")
    object Favotite: Screen("Favorites")
}
