package org.newsapi.newsarticles.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.newsapi.newsarticles.R
import org.newsapi.newsarticles.common.navigation.NavGraph
import org.newsapi.newsarticles.common.navigation.Screen
import org.newsapi.newsarticles.common.ui.BottomNavigationItem
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

                val screens = listOf(
                    Screen.NewsArticle,
                    Screen.Favotite
                )

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    var selectedItemIndex by rememberSaveable {
                        mutableStateOf(0)
                    }


                    val showBottomBar = navController
                        .currentBackStackEntryAsState().value?.destination?.route in screens.map { it.route }
                    Scaffold(bottomBar = {

                        if (showBottomBar) {
                            NavigationBar(tonalElevation = 32.dp) {
                                items.forEachIndexed { index, item ->
                                    NavigationBarItem(selected = selectedItemIndex == index,
                                        onClick = {

                                            selectedItemIndex = index
                                            navController.navigate(item.title) {
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
                        }

                    }) { paddingValues ->
                        NavGraph(navController = navController, paddingValues)
                    }

                }
            }
        }
    }
}
