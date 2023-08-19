package org.newsapi.newsarticles.domain.usecase

import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity
import org.newsapi.newsarticles.domain.repository.NewsArticlesLocalRepository
import javax.inject.Inject

class DeleteNewsArticleUseCase @Inject constructor(
    private val newsArticlesLocalRepository: NewsArticlesLocalRepository
) {

    suspend operator fun invoke(articleEntity: NewsArticleEntity) =
        newsArticlesLocalRepository.deleteNewsArticle(articleEntity.title!!)

}