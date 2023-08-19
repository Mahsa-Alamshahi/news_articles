package org.newsapi.newsarticles.presentation.article_details_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.newsapi.newsarticles.common.ui.UiUtils
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity
import org.newsapi.newsarticles.domain.usecase.AddNewsArticleIntoDbUseCase
import org.newsapi.newsarticles.domain.usecase.ArticleExistanceInFavoritesUseCase
import org.newsapi.newsarticles.domain.usecase.DeleteNewsArticleUseCase
import javax.inject.Inject


@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(
    private val articleExistanceInFavoritesUseCase: ArticleExistanceInFavoritesUseCase,
    private val addNewsArticleIntoDbUseCase: AddNewsArticleIntoDbUseCase,
    private val deleteNewsArticleUseCase: DeleteNewsArticleUseCase,
    private val uiUtils: UiUtils
) : ViewModel() {


    private var _isExist = MutableLiveData(false)
    var isExist: LiveData<Boolean> = _isExist


    fun isArticleExistedInFavorites(title: String) = viewModelScope.launch {
        articleExistanceInFavoritesUseCase(title).collect {
            _isExist.postValue(it)
        }
    }


    fun addNewsArticle(newsArticleEntity: NewsArticleEntity) = viewModelScope.launch {
        addNewsArticleIntoDbUseCase(newsArticleEntity)
    }


    fun deleteNewsArticle(newsArticleEntity: NewsArticleEntity) = viewModelScope.launch {
        deleteNewsArticleUseCase(newsArticleEntity)
    }


    fun checkNullableResponseField(responseFieldValue: String?): String =
        uiUtils.checkNullableApiResponse(responseFieldValue)

    fun parseHtml(responseFieldValue: String?): String = uiUtils.parseHtml(responseFieldValue)

}