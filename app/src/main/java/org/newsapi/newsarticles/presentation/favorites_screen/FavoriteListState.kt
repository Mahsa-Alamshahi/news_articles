package org.newsapi.newsarticles.presentation.favorites_screen

import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity

data class FavoriteListState(
    val isLoading: Boolean = false,
    val articles: List<NewsArticleEntity> = emptyList(),
    val error: String = ""
)
