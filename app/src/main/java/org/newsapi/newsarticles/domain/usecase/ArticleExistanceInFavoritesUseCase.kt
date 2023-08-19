package org.newsapi.newsarticles.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.newsapi.newsarticles.domain.repository.NewsArticlesLocalRepository

import javax.inject.Inject

class ArticleExistanceInFavoritesUseCase @Inject constructor(
    private val newsArticlesLocalRepository: NewsArticlesLocalRepository) {

    operator fun invoke(title: String): Flow<Boolean> = flow {
        try {
            val isExist =newsArticlesLocalRepository.isArticleExistedInFavorites(title)
            emit(isExist)
        } catch (e: Exception){
            println(e.message)
        }
    }
}