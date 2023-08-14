package org.newsapi.newsarticles.presentation.news_articles_screen.components

import org.newsapi.newsarticles.data.data_source.remote.dto.ArticleDto

data class NewsArticleListState(
    val isLoading: Boolean = false,
    val articleDtos: List<ArticleDto> = emptyList(),
    val error: String = ""
)
