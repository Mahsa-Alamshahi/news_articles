package org.newsapi.newsarticles.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.newsapi.newsarticles.common.utils.Resource
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity
import org.newsapi.newsarticles.data.data_source.remote.dto.toNewsArticle
import org.newsapi.newsarticles.domain.repository.NewsArticlesRemoteRepository
import javax.inject.Inject

class SearchInNewsArticlesUseCase @Inject constructor(private val newsArticlesRemoteRepository: NewsArticlesRemoteRepository) {


    suspend operator fun invoke(title: String): Flow<Resource<List<NewsArticleEntity>>> =
        flow {
            try {
                emit(Resource.Loading())
                val newsArticleList =
                    newsArticlesRemoteRepository.searchInNewsArticles(title).map {
                        it.toNewsArticle()
                    }
                emit(Resource.Success(newsArticleList))
            } catch (e: Exception) {
                emit(Resource.Failed("Can't load news articles."))
            }
        }.flowOn(Dispatchers.IO)
}