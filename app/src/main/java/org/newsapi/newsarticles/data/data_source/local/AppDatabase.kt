package org.newsapi.newsarticles.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [NewsArticleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun newsArticleDao(): NewsArticleDao
}