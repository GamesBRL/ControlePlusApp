package com.example.controleplus.core.util

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val description: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)
