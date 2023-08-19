package org.newsapi.newsarticles.common.ui

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val badgeCount: Int? = null
)
