package org.newsapi.newsarticles.domain.usecase

import org.newsapi.newsarticles.domain.repository.NewsArticlesLocalRepository
import javax.inject.Inject

class isArticleLikedUseCase @Inject constructor(
    private val newsArticlesLocalRepository: NewsArticlesLocalRepository
) {


    suspend operator fun invoke(title: String): Boolean =
        newsArticlesLocalRepository.isArticleExistedInFavorites(title)

}