package org.newsapi.newsarticles.common.navigation

import com.google.gson.Gson
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity

class NewsArticleDetailsNavArgs  : JsonNavType<NewsArticleEntity>() {

    override fun fromJsonParse(value: String): NewsArticleEntity= Gson().fromJson(
        value, NewsArticleEntity::class.java
    )

    override fun NewsArticleEntity.getJsonParse(): String = Gson().toJson(this)

}