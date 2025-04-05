package com.arboleda.rickmortyapp.coreUI.navigation

sealed class Routes(
    val route: String,
) {
    data object HomeScreen : Routes("HomeScreen")

    // Bottom Navigation Item
    data object EpisodesScreen : Routes("EpisodesScreen")

    data object CharactersScreen : Routes("CharactersScreen")
}
