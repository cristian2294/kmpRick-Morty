package com.arboleda.rickmortyapp.coreUI.navigation.bottomNavigation.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.arboleda.rickmortyapp.coreUI.navigation.Routes

sealed class BottomMenuItem {
    abstract val route: String
    abstract val title: String
    abstract val icon: @Composable () -> Unit

    data class Episodes(
        override val route: String = Routes.EpisodesScreen.route,
        override val title: String = "Episodes",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Menu, contentDescription = "")
        },
    ) : BottomMenuItem()

    data class Characters(
        override val route: String = Routes.CharactersScreen.route,
        override val title: String = "Characters",
        override val icon: @Composable () -> Unit = {
            Icon(imageVector = Icons.Default.Person, contentDescription = "")
        },
    ) : BottomMenuItem()
}
