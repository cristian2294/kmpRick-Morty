package com.arboleda.rickmortyapp.coreUI.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun CustomVideoPlayer(
    modifier: Modifier,
    url: String,
)
