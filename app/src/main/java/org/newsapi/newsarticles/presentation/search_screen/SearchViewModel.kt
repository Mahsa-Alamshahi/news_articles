package org.newsapi.newsarticles.presentation.search_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.newsapi.newsarticles.common.Constants
import org.newsapi.newsarticles.common.ui.UiUtils
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity
import org.newsapi.newsarticles.domain.usecase.AddNewsArticleIntoDbUseCase
import org.newsapi.newsarticles.domain.usecase.DeleteNewsArticleUseCase
import org.newsapi.newsarticles.domain.usecase.SearchInNewsArticlesUseCase
import org.newsapi.newsarticles.domain.usecase.isArticleLikedUseCase
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchInNewsArticlesUseCase: SearchInNewsArticlesUseCase,
    private val isArticleLikedUseCase: isArticleLikedUseCase,
    private val addNewsArticleIntoDbUseCase: AddNewsArticleIntoDbUseCase,
    private val deleteNewsArticleUseCase: DeleteNewsArticleUseCase,
    private val uiUtils: UiUtils
) : ViewModel() {


    var articles: MutableState<List<NewsArticleEntity>> = mutableStateOf(listOf())
    var query = mutableStateOf("")
    val loading = mutableStateOf(false)
    val page = mutableStateOf(1)
    var newsArticleListScrollPosition = 0


    init {
        newSearch()
    }


    fun newSearch() {
        viewModelScope.launch {
            Logger.d(query.value)
            loading.value = true
            resetSearchState()
            delay(2000)
            val result = searchInNewsArticlesUseCase(query.value, page.value)
                articles.value = result
            loading.value = false

        }
    }

    private fun resetSearchState() {
        articles.value = emptyList()
        page.value = 1
        onChangeArticleScrollPosition(0)
    }


    fun nextPage(){
        viewModelScope.launch {
            if ((newsArticleListScrollPosition + 1 ) >= (page.value *Constants.PAGE_SIZE)) {
                loading.value = true
                incrementPage()
                delay(1000)
                if (page.value > 1) {
                    val result = searchInNewsArticlesUseCase(query.value, page.value)
                      appendArticles(result)
                }
                loading.value = false
            }
        }
    }

    private fun appendArticles(articles: List<NewsArticleEntity>) {
        val current = ArrayList(this.articles.value)
        current.addAll(articles)
        this.articles.value = current
    }

    private fun incrementPage() {
        page.value = page.value + 1
    }


    fun onChangeArticleScrollPosition(position: Int) {
        newsArticleListScrollPosition = position
    }



    suspend fun isArticleExistedInFavorites(title: String): Boolean {
     return isArticleLikedUseCase(title)
    }


    fun addNewsArticle(newsArticleEntity: NewsArticleEntity) = viewModelScope.launch {
        addNewsArticleIntoDbUseCase(newsArticleEntity)
    }


    fun deleteNewsArticle(newsArticleEntity: NewsArticleEntity) = viewModelScope.launch {
        deleteNewsArticleUseCase(newsArticleEntity)
    }

    fun checkNullableResponseField(responseFieldValue: String?): String =
        uiUtils.checkNullableApiResponse(responseFieldValue)



}