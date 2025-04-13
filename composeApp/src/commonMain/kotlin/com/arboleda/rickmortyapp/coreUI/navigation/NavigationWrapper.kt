package com.arboleda.rickmortyapp.coreUI.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.arboleda.rickmortyapp.ui.screens.home.HomeScreen

@Composable
fun NavigationWrapper() {
    val mainNavController = rememberNavController()

    NavHost(navController = mainNavController, startDestination = Routes.HomeScreen.route) {
        composable(route = Routes.HomeScreen.route) {
            HomeScreen()
        }
    }
}
