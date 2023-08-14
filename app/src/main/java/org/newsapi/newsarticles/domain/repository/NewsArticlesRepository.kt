package org.newsapi.newsarticles.domain.repository

import org.newsapi.newsarticles.data.data_source.remote.dto.ArticleDto

interface NewsArticlesRepository {

    suspend fun searchInNewsArticles(title: String): List<ArticleDto>
}