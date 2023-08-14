package org.newsapi.newsarticles.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.newsapi.newsarticles.R
import org.newsapi.newsarticles.common.navigation.BottomNavigationItem
import org.newsapi.newsarticles.common.navigation.NavGraph
import org.newsapi.newsarticles.common.ui.theme.NewsArticlesTheme

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsArticlesTheme {

                val items = listOf(
                    BottomNavigationItem(
                        stringResource(id = R.string.news_articles), Icons.Default.Search
                    ),
                    BottomNavigationItem(
                        stringResource(id = R.string.favorites), Icons.Default.Favorite
                    ),
                )

                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    var selectedItemIndex by rememberSaveable {
                        mutableStateOf(0)
                    }
                    Scaffold(bottomBar = {

                        NavigationBar {
                            items.forEachIndexed { index, item ->
                                NavigationBarItem(selected = selectedItemIndex == index,
                                    onClick = {
                                        selectedItemIndex = index
                                        println("News Article in ${item.title}")
                                        navController.navigate(item.title){
                                            navController.graph.startDestinationRoute?.let { route ->
                                                popUpTo(route) {
                                                    saveState = true
                                                }
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    label = {
                                        Text(text = item.title)
                                    },
                                    alwaysShowLabel = false,
                                    icon = {
                                        BadgedBox(badge = {
                                            if (item.badgeCount != null) {
                                                Badge {
//                                                        Text(text = item.badgeCount.toString())
                                                }
                                            } else {
                                                Badge()
                                            }
                                        }) {
                                            Icon(
                                                imageVector = if (selectedItemIndex == index) {
                                                    item.selectedIcon
                                                } else {
                                                    item.selectedIcon
                                                }, contentDescription = ""
                                            )
                                        }
                                    })
                            }
                        }

                    }) { paddingValues ->
                        NavGraph(navController = navController)
                    }

                }
            }
        }
    }
}
