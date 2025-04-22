package com.arboleda.rickmortyapp.core.ext

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.setAliveBorder(isAlive: Boolean): Modifier {
    val color = if (isAlive) Color.Green else Color.Red
    return border(4.dp, color, CircleShape)
}
