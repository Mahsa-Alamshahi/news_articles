package org.newsapi.newsarticles.presentation.news_articles_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.newsapi.newsarticles.presentation.news_articles_screen.components.ErrorView
import org.newsapi.newsarticles.presentation.news_articles_screen.components.LoadingView
import org.newsapi.newsarticles.presentation.news_articles_screen.components.NewsArticleListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsArticlesScreen(navController: NavController, viewModel: SearchViewModel = hiltViewModel()) {


    val newsArticleListState = viewModel.newsState.value
    var value by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {


        Row(modifier= Modifier.fillMaxWidth().padding(8.dp)) {
            TextField(
                value = value,
                onValueChange = { newText ->
                    value = newText
                },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Email Icon"
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Person Icon"
                    )
                },
                label = { Text(text = "News Article") },
                placeholder = { Text(text = "Type your email") }
            )
        }



        if (newsArticleListState.isLoading) {
            LoadingView()
        } else if (newsArticleListState.error.isNotBlank()) {
            ErrorView()
        } else if (newsArticleListState.articleDtos.isEmpty()) {
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(4.dp)
            ) {
                items(items = newsArticleListState.articleDtos) { article ->
                    NewsArticleListItem(articleDto = article)
                }
            }

        }

    }

}


@Preview
@Composable
fun PreviewNewsScreen() {
    val navController = rememberNavController()
    NewsArticlesScreen(navController = navController)
}