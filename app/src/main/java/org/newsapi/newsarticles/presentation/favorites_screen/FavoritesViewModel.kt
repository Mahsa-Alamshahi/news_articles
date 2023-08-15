package org.newsapi.newsarticles.presentation.favorites_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.newsapi.newsarticles.common.utils.Resource
import org.newsapi.newsarticles.domain.usecase.GetAllNewsArticlesFromDbUseCase
import org.newsapi.newsarticles.presentation.news_articles_screen.components.NewsArticleListState
import javax.inject.Inject


@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getAllNewsArticlesFromDbUseCase: GetAllNewsArticlesFromDbUseCase
): ViewModel() {


    private val _newsListState = mutableStateOf(FavoriteListState())
    val newsListState: State<FavoriteListState> = _newsListState


    init {
      getAllNewsArticles()
    }


    private fun getAllNewsArticles() {
        viewModelScope.launch {
            val result = getAllNewsArticlesFromDbUseCase()
            result.collect { resultData ->

                when (resultData) {
                    is Resource.Success -> {
                        _newsListState.value = FavoriteListState(articles = resultData.data ?: emptyList())
                    }

                    is Resource.Loading -> {
                        _newsListState.value = FavoriteListState(isLoading = true)
                    }

                    is Resource.Failed -> {
                        _newsListState.value =
                            FavoriteListState(error = resultData.message ?: "An error accured.")
                    }
                }
            }

        }
    }





}