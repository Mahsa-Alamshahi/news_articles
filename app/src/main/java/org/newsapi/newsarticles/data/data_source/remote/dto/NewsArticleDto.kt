package org.newsapi.newsarticles.data.data_source.remote.dto

import com.google.gson.annotations.SerializedName

data class NewsArticleDto(
    @SerializedName("articles")
    val articleDtos: List<ArticleDto>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)


