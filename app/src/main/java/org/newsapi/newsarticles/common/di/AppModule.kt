package org.newsapi.newsarticles.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.newsapi.newsarticles.data.data_source.remote.ApiService
import org.newsapi.newsarticles.data.repository.NewsArticlesRepositoryImpl
import org.newsapi.newsarticles.domain.repository.NewsArticlesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideSearchInNewsArticlesRepository(apiService: ApiService): NewsArticlesRepository =
        NewsArticlesRepositoryImpl(apiService)
}