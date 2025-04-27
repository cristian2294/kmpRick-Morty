package com.arboleda.rickmortyapp.core.ext

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arboleda.rickmortyapp.coreUI.Green

fun Modifier.setAliveBorder(isAlive: Boolean): Modifier {
    val color = if (isAlive) Green else Color.Red
    return border(4.dp, color, CircleShape)
}
