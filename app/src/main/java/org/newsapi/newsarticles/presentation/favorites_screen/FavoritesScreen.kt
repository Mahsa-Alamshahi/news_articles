package org.newsapi.newsarticles.presentation.favorites_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.newsapi.newsarticles.R
import org.newsapi.newsarticles.common.ui.AnimationLoader
import org.newsapi.newsarticles.common.ui.EmptyList
import org.newsapi.newsarticles.data.data_source.local.NewsArticleEntity
import org.newsapi.newsarticles.presentation.favorites_screen.components.FavoriteListItem

@Composable
fun FavoritesScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: FavoritesViewModel = hiltViewModel()
) {


    val state: MutableState<List<NewsArticleEntity>> = remember { mutableStateOf(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }



    LaunchedEffect(Unit) {
        isLoading = true
        withContext(Dispatchers.IO) {
            state.value = viewModel.getFavoriteList()
            isLoading = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        if (isLoading) {

            Row(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                AnimationLoader(width = 64.dp, height = 64.dp, resId = R.raw.loading_anim)
            }
        } else if (state.value.isEmpty()) {
            EmptyList(R.raw.favorite_anim)
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(4.dp)
            ) {
                items(items = state.value) { article ->
                    FavoriteListItem(navController = navController, article = article)
                }
            }
        }
    }
}

