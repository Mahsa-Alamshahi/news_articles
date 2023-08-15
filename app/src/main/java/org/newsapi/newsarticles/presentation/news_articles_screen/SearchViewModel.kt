package org.newsapi.newsarticles.presentation.news_articles_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.newsapi.newsarticles.common.utils.Resource
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity
import org.newsapi.newsarticles.domain.usecase.AddNewsArticleIntoDbUseCase
import org.newsapi.newsarticles.domain.usecase.SearchInNewsArticlesUseCase
import org.newsapi.newsarticles.presentation.news_articles_screen.components.NewsArticleListState
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val searchInNewsArticlesUseCase: SearchInNewsArticlesUseCase,
    private val addNewsArticleIntoDbUseCase: AddNewsArticleIntoDbUseCase) :
    ViewModel() {


    private val _newsState = mutableStateOf(NewsArticleListState())
    val newsState: State<NewsArticleListState> = _newsState


    init {
        searchInNewsArticles("bitcoin")
    }


        fun searchInNewsArticles(title: String) {
            viewModelScope.launch {
                val result = searchInNewsArticlesUseCase(title)
                result.collect { resultData ->

                    when (resultData) {
                        is Resource.Success -> {
                            _newsState.value = NewsArticleListState(articleDtos = resultData.data ?: emptyList())
                        }

                        is Resource.Loading -> {
                            _newsState.value = NewsArticleListState(isLoading = true)
                        }

                        is Resource.Failed -> {
                            _newsState.value =
                                NewsArticleListState(error = resultData.message ?: "An error accured.")
                        }
                    }
                }

            }
        }



    fun addNewsArticle(newsArticleEntity: NewsArticleEntity){
        viewModelScope.launch {
            addNewsArticleIntoDbUseCase(newsArticleEntity)
        }
    }



}