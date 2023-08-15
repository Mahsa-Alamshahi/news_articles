package org.newsapi.newsarticles.presentation.news_articles_screen.components

import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity

data class NewsArticleListState(
    val isLoading: Boolean = false,
    val articleDtos: List<NewsArticleEntity> = emptyList(),
    val error: String = ""
)
