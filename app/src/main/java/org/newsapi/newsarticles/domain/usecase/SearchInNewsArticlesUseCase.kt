package org.newsapi.newsarticles.domain.usecase

import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity
import org.newsapi.newsarticles.data.data_source.remote.dto.toNewsArticle
import org.newsapi.newsarticles.domain.repository.NewsArticlesRemoteRepository
import javax.inject.Inject


class SearchInNewsArticlesUseCase @Inject constructor(
    private val newsArticlesRemoteRepository: NewsArticlesRemoteRepository
) {

    suspend operator fun invoke(title: String, page: Int): List<NewsArticleEntity> =
        newsArticlesRemoteRepository.searchInNewsArticles(title, page).map {
                it.toNewsArticle()
            }

}

