package org.newsapi.newsarticles.common.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.newsapi.newsarticles.common.Constants.DB_NAME
import org.newsapi.newsarticles.data.data_source.local.AppDatabase
import org.newsapi.newsarticles.data.data_source.local.NewsArticleDao
import org.newsapi.newsarticles.data.data_source.remote.ApiService
import org.newsapi.newsarticles.data.repository.NewsArticlesLocalRepositoryImpl
import org.newsapi.newsarticles.data.repository.NewsArticlesRemoteRepositoryImpl
import org.newsapi.newsarticles.domain.repository.NewsArticlesLocalRepository
import org.newsapi.newsarticles.domain.repository.NewsArticlesRemoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext application: Context): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, DB_NAME)
            .allowMainThreadQueries().build()
    }


    @Singleton
    @Provides
    fun provideNewsArticleDao(db: AppDatabase) = db.newsArticleDao()

    @Provides
    @Singleton
    fun provideSearchInNewsArticlesLocalRepository(newsArticleDao: NewsArticleDao): NewsArticlesLocalRepository =
        NewsArticlesLocalRepositoryImpl(newsArticleDao)
}