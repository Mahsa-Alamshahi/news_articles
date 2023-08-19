package org.newsapi.newsarticles.data.repository

import org.newsapi.newsarticles.data.data_source.remote.ApiService
import org.newsapi.newsarticles.data.data_source.remote.dto.ArticleDto
import org.newsapi.newsarticles.data.data_source.remote.dto.NewsArticleDto
import org.newsapi.newsarticles.domain.repository.NewsArticlesRemoteRepository
import retrofit2.HttpException
import javax.inject.Inject

class NewsArticlesRemoteRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    NewsArticlesRemoteRepository {


    override suspend fun searchInNewsArticles(title: String, page: Int): List<ArticleDto> {

        var response = NewsArticleDto(emptyList(), "", 1)
        try {
            response = apiService.searchInNewsArticles(query = title, page = page)
        } catch (e: HttpException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return response.articleDtos
    }
}