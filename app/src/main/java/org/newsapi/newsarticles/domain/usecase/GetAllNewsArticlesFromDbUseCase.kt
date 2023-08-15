package org.newsapi.newsarticles.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.newsapi.newsarticles.common.utils.Resource
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity
import org.newsapi.newsarticles.domain.repository.NewsArticlesLocalRepository
import java.lang.Exception
import javax.inject.Inject

class GetAllNewsArticlesFromDbUseCase @Inject constructor(private val newsArticlesLocalRepository: NewsArticlesLocalRepository) {


    suspend operator fun invoke(): Flow<Resource<List<NewsArticleEntity>>> = flow {
        try {
            emit(Resource.Loading())
            val newsArticleList =
                newsArticlesLocalRepository.getAllNewsArticles()
            emit(Resource.Success(newsArticleList))
        } catch (e: Exception) {
            emit(Resource.Failed("Can't load news articles."))
        }
    }

}