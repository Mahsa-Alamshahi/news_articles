package org.newsapi.newsarticles.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.newsapi.newsarticles.common.utils.Resource
import org.newsapi.newsarticles.data.data_source.remote.dto.ArticleDto
import org.newsapi.newsarticles.domain.repository.NewsArticlesRepository
import javax.inject.Inject

class SearchInNewsArticlesUseCase @Inject constructor(private val newsArticlesRepository: NewsArticlesRepository) {


    suspend operator fun invoke(title: String): Flow<Resource<List<ArticleDto>>> =
        flow {
            println("News Article in Usecase")
            try {
                emit(Resource.Loading())
                val newsArticleList =
                    newsArticlesRepository.searchInNewsArticles(title)
                println("News Article in Flow")
                println("USECASE  +++++++++++++   ${newsArticleList.size}")
                emit(Resource.Success(newsArticleList))
            } catch (e: Exception) {
                emit(Resource.Failed("Can't load news articles."))
            }
        }.flowOn(Dispatchers.IO)
}