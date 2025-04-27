package com.arboleda.rickmortyapp.coreUI

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val BackgroundPrimaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) PrimaryDark else PrimaryLight

val BackgroundSecondaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) SecondaryDark else SecondaryLight

val BackgroundTertiaryColor
    @Composable
    get() = if (isSystemInDarkTheme()) TertiaryDark else TertiaryLight

val DefaultTextColor
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.Black

val PlaceholderColor
    @Composable
    get() = if (isSystemInDarkTheme()) TertiaryDark else SecondaryLight

val Pink = Color(0xFFFF577D)
val Green = Color(0xFF5CCF92)

val PrimaryLight = Color(0xFFFFFFFF)
val SecondaryLight = Color(0xFFEAE8EF)
val TertiaryLight = Color(0xFFFAFAFA)

val PrimaryDark = Color(0xFF000000)
val SecondaryDark = Color(0xFF302F2F)
val TertiaryDark = Color(0xFF464646)
