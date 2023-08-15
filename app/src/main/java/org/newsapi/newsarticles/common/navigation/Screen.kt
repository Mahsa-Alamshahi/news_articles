package org.newsapi.newsarticles.common.navigation

import org.newsapi.newsarticles.common.Constants.NEWS_ARTICLE_DETAILS_ARGUMENT_KEY
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity

sealed class Screen(val route: String) {

    object NewsArticle: Screen(route = "News Articles")
    object Favotite: Screen("Favorites")

    object NewsArticleDetails: Screen(route = "article_details_screen?$NEWS_ARTICLE_DETAILS_ARGUMENT_KEY={$NEWS_ARTICLE_DETAILS_ARGUMENT_KEY}"){
        fun passArticle(article: String?) =
            "article_details_screen?$NEWS_ARTICLE_DETAILS_ARGUMENT_KEY=$article"
    }
}
