package org.newsapi.newsarticles.presentation.favorites_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity
import org.newsapi.newsarticles.domain.usecase.GetAllFavoritesNewsArticlesFromDbUseCase
import javax.inject.Inject


@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getAllFavoritesNewsArticlesFromDbUseCase: GetAllFavoritesNewsArticlesFromDbUseCase
): ViewModel() {




    suspend fun getFavoriteList(): List<NewsArticleEntity> {
      return getAllFavoritesNewsArticlesFromDbUseCase()
    }

}