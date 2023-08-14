package org.newsapi.newsarticles.data.repository

import org.newsapi.newsarticles.data.data_source.remote.ApiService
import org.newsapi.newsarticles.data.data_source.remote.dto.ArticleDto
import org.newsapi.newsarticles.domain.repository.NewsArticlesRepository
import javax.inject.Inject

class NewsArticlesRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    NewsArticlesRepository {


    override suspend fun searchInNewsArticles(title: String): List<ArticleDto> {
        var result: List<ArticleDto> = ArrayList()
        var response = apiService.searchInNewsArticles(title)
       println("API CALL ${response}")

            if (response.status == "ok"){
             result = response.articleDtos
            }

        return result
    }
}