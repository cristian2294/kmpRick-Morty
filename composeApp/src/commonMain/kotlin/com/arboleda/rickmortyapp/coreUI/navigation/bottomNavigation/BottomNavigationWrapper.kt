package com.arboleda.rickmortyapp.coreUI.navigation.bottomNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.arboleda.rickmortyapp.coreUI.navigation.Routes
import com.arboleda.rickmortyapp.ui.screens.home.tabs.characters.CharactersScreen
import com.arboleda.rickmortyapp.ui.screens.home.tabs.episodes.EpisodesScreen

@Composable
fun BottomNavigationWrapper(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.EpisodesScreen.route) {
        composable(route = Routes.EpisodesScreen.route) {
            EpisodesScreen()
        }
        composable(route = Routes.CharactersScreen.route) {
            CharactersScreen()
        }
    }
}
