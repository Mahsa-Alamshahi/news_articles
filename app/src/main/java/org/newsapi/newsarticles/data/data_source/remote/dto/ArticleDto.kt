package org.newsapi.newsarticles.data.data_source.remote.dto

import com.google.gson.annotations.SerializedName
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity

data class ArticleDto(
    @SerializedName("author")
    val author: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("urlToImage")
    val urlToImage: String
)



fun ArticleDto.toNewsArticle(): NewsArticleEntity {
    return NewsArticleEntity(name = source.name,
        author= author,
        content = content,
        publishedAt = publishedAt,
        title = title,
        description = description,
        url = url,
        imageUrl = urlToImage)
}