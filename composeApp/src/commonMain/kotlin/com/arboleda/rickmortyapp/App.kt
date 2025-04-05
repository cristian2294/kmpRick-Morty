package com.arboleda.rickmortyapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.arboleda.rickmortyapp.coreUI.navigation.NavigationWrapper
import org.jetbrains.compose.ui.tooling.preview.Preview

@Suppress("ktlint:standard:function-naming")
@Composable
@Preview
fun App() {
    MaterialTheme {
        NavigationWrapper()
    }
}
