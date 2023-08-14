package org.newsapi.newsarticles.data.data_source.local

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "news_article")
data class NewsArticle(

    @ColumnInfo(name = "name") var name: String? = null,
    @ColumnInfo(name = "author") var author: String? = null,
    @ColumnInfo(name = "content") var content: String? = null,
    @ColumnInfo(name = "description") var description: String? = null,
    @ColumnInfo(name = "published_at") var publishedAt: String? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "url") var url: String? = null,
    @ColumnInfo(name = "image_url") var imageUrl: String? = null,

    )
