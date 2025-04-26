package com.arboleda.rickmortyapp.coreUI.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.arboleda.rickmortyapp.coreUI.BackgroundPrimaryColor
import com.arboleda.rickmortyapp.coreUI.BackgroundTertiaryColor
import com.arboleda.rickmortyapp.coreUI.DefaultTextColor
import com.arboleda.rickmortyapp.coreUI.Green
import com.arboleda.rickmortyapp.coreUI.navigation.bottomNavigation.model.BottomMenuItem

@Composable
fun BottomMenu(
    listItems: List<BottomMenuItem>,
    navController: NavHostController,
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        containerColor = BackgroundPrimaryColor,
        contentColor = Green,
    ) {
        listItems.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.all { it.route == currentDestination.route } == true,
                label = { Text(text = item.title, color = DefaultTextColor) },
                colors =
                    NavigationBarItemDefaults.colors(
                        indicatorColor = Green,
                        selectedIconColor = BackgroundTertiaryColor,
                        unselectedIconColor = Green,
                    ),
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = item.icon,
            )
        }
    }
}
