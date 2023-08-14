package org.newsapi.newsarticles.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface NewsArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsArticle(article: NewsArticle)
}