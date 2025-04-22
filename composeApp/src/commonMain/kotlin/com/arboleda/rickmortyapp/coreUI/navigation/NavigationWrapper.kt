package com.arboleda.rickmortyapp.coreUI.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.arboleda.rickmortyapp.domain.model.Character
import com.arboleda.rickmortyapp.ui.screens.CharacterDetailScreen
import com.arboleda.rickmortyapp.ui.screens.home.HomeScreen
import kotlinx.serialization.json.Json

@Composable
fun NavigationWrapper() {
    val mainNavController = rememberNavController()

    NavHost(navController = mainNavController, startDestination = Routes.HomeScreen.route) {
        composable(route = Routes.HomeScreen.route) {
            HomeScreen(mainNavController = mainNavController)
        }

        composable<CharacterDetail> { navBackStackEntry ->
            val characterDetail = navBackStackEntry.toRoute<CharacterDetail>()
            val character = Json.decodeFromString<Character>(characterDetail.character)
            CharacterDetailScreen(character = character)
        }
    }
}
