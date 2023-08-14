package org.newsapi.newsarticles.presentation.favorites_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun FavoritesScreen(navController: NavController) {
    Column(modifier = Modifier
        .background(color = Color.Red)
        .fillMaxSize()) {

    }

}


@Preview
@Composable
fun PreviewFavoriteScreen(){
    val navController = rememberNavController()
    FavoritesScreen(navController = navController)
}