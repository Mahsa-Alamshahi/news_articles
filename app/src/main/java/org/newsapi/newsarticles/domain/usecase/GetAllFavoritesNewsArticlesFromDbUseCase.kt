package org.newsapi.newsarticles.domain.usecase

import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity
import org.newsapi.newsarticles.domain.repository.NewsArticlesLocalRepository
import javax.inject.Inject

class GetAllFavoritesNewsArticlesFromDbUseCase @Inject constructor(
    private val newsArticlesLocalRepository: NewsArticlesLocalRepository
) {


    suspend operator fun invoke(): List<NewsArticleEntity> =
        newsArticlesLocalRepository.getAllNewsArticles()


}