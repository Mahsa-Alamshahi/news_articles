package org.newsapi.newsarticles.domain.repository

import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity

interface NewsArticlesLocalRepository {

    suspend fun addNewsArticle(article: NewsArticleEntity)
    suspend fun getAllNewsArticles(): List<NewsArticleEntity>
}