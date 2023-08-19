package org.newsapi.newsarticles.data.repository

import org.newsapi.newsarticles.data.data_source.local.NewsArticleDao
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity
import org.newsapi.newsarticles.domain.repository.NewsArticlesLocalRepository
import javax.inject.Inject

class NewsArticlesLocalRepositoryImpl @Inject constructor(private val newsArticleDao: NewsArticleDao) :
    NewsArticlesLocalRepository {
    override suspend fun addNewsArticle(article: NewsArticleEntity) {
        newsArticleDao.insertNewsArticle(article)
    }

    override suspend fun getAllNewsArticles(): List<NewsArticleEntity> = newsArticleDao.getAllNewsArticles()
    override suspend fun isArticleExistedInFavorites(title: String): Boolean = newsArticleDao.isArticleExisted(title)
    override suspend fun deleteNewsArticle(title: String) = newsArticleDao.deleteNewsArticle(title)


}