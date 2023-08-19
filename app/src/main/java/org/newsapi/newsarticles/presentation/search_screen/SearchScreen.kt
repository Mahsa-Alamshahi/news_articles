package org.newsapi.newsarticles.presentation.search_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.newsapi.newsarticles.R
import org.newsapi.newsarticles.common.Constants
import org.newsapi.newsarticles.common.ui.AnimationLoader
import org.newsapi.newsarticles.common.ui.EmptyList
import org.newsapi.newsarticles.presentation.search_screen.components.NewsArticleListItem

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun NewsArticlesScreen(
    navController: NavController,
    paddingValues: PaddingValues,
    viewModel: SearchViewModel = hiltViewModel()
) {


    var query by remember {
        mutableStateOf(viewModel.query.value)
    }

    val articles = viewModel.articles.value
    val loading = viewModel.loading.value
    val page = viewModel.page.value


    var value by remember {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            TextField(value = query,
                onValueChange = { newText ->
                    value = newText
                    query = newText
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        BorderStroke(1.dp, color = Color.Gray), shape = RoundedCornerShape(8.dp)
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    cursorColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(8.dp),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_newspaper_24),
                        contentDescription = "News Icon"
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search, contentDescription = "Search Icon",
                        modifier = Modifier.clickable {
                            keyboardController?.hide()
                            viewModel.query.value = query
                            viewModel.newSearch()

                        }
                    )
                },

                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        keyboardController?.hide()
                        viewModel.query.value = query
                        viewModel.newSearch()
                    },
                    onDone = {
                        keyboardController?.hide()
                        viewModel.query.value = query
                        viewModel.newSearch()
                    },
                    onGo = {
                        keyboardController?.hide()
                        viewModel.query.value = query
                        viewModel.newSearch()
                    },
                    onNext = {
                        keyboardController?.hide()
                        viewModel.query.value = query
                        viewModel.newSearch()
                    },
                    onSend = {
                        keyboardController?.hide()
                        viewModel.query.value = query
                        viewModel.newSearch()
                    },

                ),
                label = { Text(text = "News Article") },
                placeholder = { Text(text = "Enter article title.") }

            )
        }


        if (loading && articles.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AnimationLoader(resId = R.raw.loading_anim)
            }
        } else if(!loading && articles.isEmpty()){
           EmptyList(R.raw.news_anim)
        } else {
            LazyColumn {
                itemsIndexed(items = articles) { index, article ->
                    viewModel.onChangeArticleScrollPosition(index)
                    if ((index + 1) >= (page * Constants.PAGE_SIZE) && !loading) {
                        viewModel.nextPage()
                    }
                    NewsArticleListItem(
                        navController = navController,
                        article = article,
                        viewModel
                    )
                }
            }
        }
    }
}

