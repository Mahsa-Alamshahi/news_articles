package org.newsapi.newsarticles.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsArticle(article: NewsArticleEntity)


    @Query("Select * from news_article")
    suspend fun getAllNewsArticles(): List<NewsArticleEntity>


    @Query("SELECT EXISTS(SELECT 1 FROM news_article WHERE title = :title LIMIT 1)")
    suspend fun isArticleExisted(title: String): Boolean


    @Query("DELETE FROM news_article WHERE title = :title")
    suspend fun deleteNewsArticle(title: String)
}