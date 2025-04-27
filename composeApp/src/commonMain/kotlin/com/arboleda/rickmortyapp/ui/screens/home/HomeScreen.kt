package com.arboleda.rickmortyapp.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arboleda.rickmortyapp.coreUI.components.BottomMenu
import com.arboleda.rickmortyapp.coreUI.navigation.bottomNavigation.BottomNavigationWrapper
import com.arboleda.rickmortyapp.coreUI.navigation.bottomNavigation.model.BottomMenuItem.Characters
import com.arboleda.rickmortyapp.coreUI.navigation.bottomNavigation.model.BottomMenuItem.Episodes

@Composable
fun HomeScreen(mainNavController: NavHostController) {
    val listItems = listOf(Episodes(), Characters())
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomMenu(listItems = listItems, navController = navController)
        },
    ) {
        Box {
            BottomNavigationWrapper(bottomWrapperNavController = navController, mainNavController = mainNavController)
        }
    }
}
