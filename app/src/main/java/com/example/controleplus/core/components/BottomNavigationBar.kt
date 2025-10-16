package com.example.controleplus.core.components

import androidx.compose.foundation.background
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.controleplus.core.util.BottomNavigationItem
import com.example.controleplus.ui.theme.DarkGray
import com.example.controleplus.ui.theme.White50percent

@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry.value?.destination?.route

    NavigationBar(
        modifier = modifier,
        containerColor = DarkGray
    ) {
        items.forEach { item ->

            val selected = currentDestination == item.route

            NavigationBarItem(
                modifier = Modifier.background(DarkGray),
                selected = selected,
                onClick = {
                    if (!selected) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.description,
                        tint = if (selected) DarkGray else White50percent
                    )
                },
            )
        }
    }
}