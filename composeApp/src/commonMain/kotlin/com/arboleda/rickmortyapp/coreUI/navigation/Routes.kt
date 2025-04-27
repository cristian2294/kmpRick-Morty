package com.arboleda.rickmortyapp.coreUI.navigation

import kotlinx.serialization.Serializable

sealed class Routes(
    val route: String,
) {
    data object HomeScreen : Routes("HomeScreen")

    // Bottom Navigation Item
    data object EpisodesScreen : Routes("EpisodesScreen")

    data object CharactersScreen : Routes("CharactersScreen")
}

// Character Detail
@Serializable
data class CharacterDetail(
    val character: String,
)
