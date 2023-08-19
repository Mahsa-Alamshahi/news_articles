package org.newsapi.newsarticles.data.data_source.remote

import org.newsapi.newsarticles.common.Constants.API_KEY
import org.newsapi.newsarticles.common.Constants.PAGE_SIZE
import org.newsapi.newsarticles.data.data_source.remote.dto.NewsArticleDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("everything")
    suspend fun searchInNewsArticles(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("pageSize") pageSize: Int = PAGE_SIZE
    ): NewsArticleDto

}